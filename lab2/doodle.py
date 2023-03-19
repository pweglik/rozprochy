from fastapi import FastAPI
from typing import List, Dict
from pydantic import BaseModel
from fastapi.responses import JSONResponse
from fastapi import Body, FastAPI, status

app = FastAPI()

# sample requests and queries
@app.get("/")
async def root():
    return {"message": "Hello World"}


class PollRequest(BaseModel):
    question: str
    possible_answers: List[str]


class VoteRequest(BaseModel):
    answer_id: int


class PollResponse(BaseModel):
    question: str
    possible_answers: List[str]


class VoteReponse(BaseModel):
    answer_id: int


class Poll:
    def __init__(self, question: str, possible_answers: List[str]) -> None:
        self.question = question
        self.possible_answers = possible_answers
        self.votes = {}


class Vote:
    def __init__(self, answer_id: int) -> None:
        self.answer_id = answer_id


polls: Dict[int, Poll] = {}


@app.get("/poll")
async def get_polls():
    return polls


@app.post("/poll")
async def create_poll(new_poll: PollRequest):
    last_id = max(polls.keys()) if polls.keys() else 0
    polls[last_id + 1] = Poll(new_poll.question, new_poll.possible_answers)
    print(polls)
    return JSONResponse(status_code=status.HTTP_200_OK)


@app.get("/poll/{id}")
async def get_poll(id: int):
    if id in polls:
        return polls[id]
    else:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND)


@app.put("/poll/{id}")
async def put_poll(id: int, updated_poll: PollRequest):
    if id in polls:
        polls[id] = Poll(updated_poll.question, updated_poll.possible_answers)
        return JSONResponse(status_code=status.HTTP_200_OK)
    else:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST)


@app.delete("/poll/{id}")
async def delete_poll(id: int):
    if id in polls:
        del polls[id]
        return JSONResponse(status_code=status.HTTP_200_OK)
    else:
        return JSONResponse(status_code=status.HTTP_400_BAD_REQUEST)


@app.get("/poll/{id}/vote")
async def get_votes(id: int):
    if id in polls:
        return polls[id].votes
    else:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND)


@app.post("/poll/{id}/vote")
async def post_vote(id: int, new_vote: VoteRequest):
    if id in polls:
        last_id = max(polls[id].votes.keys()) if polls[id].votes.keys() else 0
        polls[id].votes[last_id + 1] = Vote(new_vote.answer_id)
        return JSONResponse(status_code=status.HTTP_200_OK)
    else:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND)


@app.get("/poll/{id}/vote/{vote_id}")
async def get_vote(id: int, vote_id):
    if id in polls and vote_id in polls[id].votes:
        return polls[id].votes[vote_id]
    else:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND)


@app.post("/poll/{id}/vote/{vote_id}")
async def put_vote(id: int, vote_id: int, updated_vote: VoteRequest):
    if id in polls and vote_id in polls[id].votes:
        polls[id].votes[vote_id] = Vote(updated_vote.answer_id)
        return JSONResponse(status_code=status.HTTP_200_OK)
    else:
        return JSONResponse(status_code=status.HTTP_404_NOT_FOUND)
