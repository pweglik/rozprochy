import socket
import threading
from typing import List, Dict, Tuple, NewType

Address = NewType("Address", Tuple[str, int])

HOST = "127.0.0.1"
PORT = 10000

threads: List[threading.Thread] = []
clients: Dict[Address, socket.socket] = {}


def thread(connection_socket: socket.socket, client_address: Address):

    print(f"Connected by {client_address}")
    while True:
        try:
            message = connection_socket.recv(1024)
            for address, socket in clients.items():
                if address != client_address:
                    try:
                        socket.sendall(message)
                    except:
                        if address in clients:
                            del clients[address]
                        socket.close()

        except:
            if client_address in clients:
                del clients[client_address]
            connection_socket.close()

        
def udp_thread(udp_socket: socket.socket):
    while True:
        message, client_address = udp_socket.recvfrom(1024)
        print("received udp")
        for address in clients:
            if address != client_address:
                udp_socket.sendto(message, address)

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as tcp_socket:
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as udp_socket:
        udp_socket.bind((HOST, PORT))

        new_udp_thread = threading.Thread(target=udp_thread, args=(udp_socket,))

        tcp_socket.bind((HOST, PORT))
        tcp_socket.listen()
        while True:
            connection_socket, client_address = tcp_socket.accept()

            clients[client_address] = connection_socket

            new_thread = threading.Thread(
                target=thread, args=(connection_socket, client_address)
            )
            threads.append(new_thread)
            new_thread.start()
