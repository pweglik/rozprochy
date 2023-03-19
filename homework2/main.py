import requests
from fastapi import FastAPI, Request
from fastapi.templating import Jinja2Templates
from fastapi.responses import HTMLResponse
from datetime import datetime, timedelta
import asyncio

app = FastAPI()

templates = Jinja2Templates(directory="templates")

correct_api_key = "agh"

paris_coords = {"lat": 48.85341, "lon": 2.3488}
beijing_coords = {"lat": 39.9075, "lon": 116.39723}
geneva_coords = {"lat": 46.20222, "lon": 6.14569}


async def get_data(lat: float, lon: float):
    today = datetime.today().strftime("%Y-%m-%d")
    week_ago = (datetime.today() - timedelta(days=7)).strftime("%Y-%m-%d")
    url = f"https://api.openaq.org/v2/measurements?date_from={week_ago}&date_to={today}&limit=5000&page=1&offset=0&sort=desc&coordinates={lat}%2C{lon}&radius=10000&order_by=datetime"

    headers = {"accept": "application/json"}

    loop = asyncio.get_event_loop()
    response = await loop.run_in_executor(None, requests.get, url, headers)

    return response.json()


def process_data(data):
    request_results = data["results"]

    result = {
        "pm1": 0,
        "pm1_count": 0,
        "pm10": 0,
        "pm10_count": 0,
        "pm25": 0,
        "pm25_count": 0,
    }

    for record in request_results:
        if record["parameter"] == "pm1":
            result["pm1"] += record["value"]
            result["pm1_count"] += 1
        elif record["parameter"] == "pm10":
            result["pm10"] += record["value"]
            result["pm10_count"] += 1
        elif record["parameter"] == "pm25":
            result["pm25"] += record["value"]
            result["pm25_count"] += 1

    return {
        "pm1": result["pm1"] / result["pm1_count"] if result["pm1_count"] != 0 else 0,
        "pm10": result["pm10"] / result["pm10_count"]
        if result["pm10_count"] != 0
        else 0,
        "pm25": result["pm25"] / result["pm25_count"]
        if result["pm25_count"] != 0
        else 0,
    }


@app.get("/", response_class=HTMLResponse)
async def root(request: Request):
    return templates.TemplateResponse("index.html", {"request": request})


@app.get("/get_measurements", response_class=HTMLResponse)
async def get_measurements(request: Request, apikey: str, lat: float, lon: float):

    if apikey != correct_api_key:
        return templates.TemplateResponse(
            "incorrect_api_key.html", {"request": request}
        )

    data = await get_data(lat, lon)

    error = None
    if not "results" in data:
        error = ("WRONG REQUEST",)
        return templates.TemplateResponse(
            "response.html", {"request": request, "error": error}
        )
    else:
        result = process_data(data)

        data = await get_data(paris_coords["lat"], paris_coords["lon"])
        paris_result = process_data(data)
        data = await get_data(beijing_coords["lat"], beijing_coords["lon"])
        beijing_result = process_data(data)
        data = await get_data(geneva_coords["lat"], geneva_coords["lon"])
        geneva_result = process_data(data)

        return templates.TemplateResponse(
            "response.html",
            {
                "request": request,
                "error": error,
                "pm1": result["pm1"],
                "pm10": result["pm10"],
                "pm25": result["pm25"],
                "paris_pm1": paris_result["pm1"],
                "paris_pm10": paris_result["pm10"],
                "paris_pm25": paris_result["pm25"],
                "beijing_pm1": beijing_result["pm1"],
                "beijing_pm10": beijing_result["pm10"],
                "beijing_pm25": beijing_result["pm25"],
                "geneva_pm1": geneva_result["pm1"],
                "geneva_pm10": geneva_result["pm10"],
                "geneva_pm25": geneva_result["pm25"],
            },
        )
