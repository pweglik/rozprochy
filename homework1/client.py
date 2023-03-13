import socket
import select
import sys

HOST = "127.0.0.1"
PORT = 10000

quit = False

nickname: str = input("Input your nickname: ")
if ";" in nickname:
    print("You can't have ; in your nickname!")
else:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as tcp_socket:
        with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as udp_socket:
            tcp_socket.connect((HOST, PORT))
            udp_socket.bind(tcp_socket.getsockname())

            while not quit:
                read, write, error = select.select(
                    [tcp_socket, udp_socket, sys.stdin], [], []
                )
                for source in read:
                    if source == tcp_socket:
                        data = str(tcp_socket.recv(1024), "utf-8")
                        delimiter_position: int = data.find(";")
                        sender_nickname, message = (
                            data[:delimiter_position],
                            data[(delimiter_position + 1) :],
                        )
                        print(f"{sender_nickname}\n{message}\n")
                    elif source == udp_socket:
                        data = str(udp_socket.recv(1024), "utf-8")
                        delimiter_position: int = data.find(";")
                        sender_nickname, message = (
                            data[:delimiter_position],
                            data[(delimiter_position + 1) :],
                        )
                        print(f"{sender_nickname}\n{message}\n")

                    else:
                        message = sys.stdin.readline()
                        if message[:3] == "<Q>":
                            quit = True
                            break  
                        if message[:3] == "<U>":
                            data = f"{nickname};{message[3:]}"
                            udp_socket.sendto(bytearray(data.encode("utf-8")), (HOST, PORT))
                        else:
                            data = f"{nickname};{message}"
                            tcp_socket.sendall(bytearray(data.encode("utf-8")))
