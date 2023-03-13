import socket
import select
import sys
import struct

HOST = "127.0.0.1"
PORT = 10000

MULTICAST_ADDRESS = "224.0.0.42"
MULTICAST_PORT = 10001

quit = False

nickname: str = input("Input your nickname: ")
if ";" in nickname:
    print("You can't have ; in your nickname!")
else:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as tcp_socket, socket.socket(
        socket.AF_INET, socket.SOCK_DGRAM
    ) as udp_socket, socket.socket(
        socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP
    ) as udp_multicast_socket:

        tcp_socket.connect((HOST, PORT))
        udp_socket.bind(tcp_socket.getsockname())

        udp_multicast_socket = socket.socket(
            socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP
        )
        udp_multicast_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        udp_multicast_socket.bind(("", MULTICAST_PORT))
        mreq = struct.pack(
            "4sl", socket.inet_aton(MULTICAST_ADDRESS), socket.INADDR_ANY
        )
        udp_multicast_socket.setsockopt(
            socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mreq
        )
        udp_multicast_socket.setsockopt(socket.IPPROTO_IP, socket.IP_MULTICAST_TTL, 2)

        while not quit:
            read, write, error = select.select(
                [tcp_socket, udp_socket, udp_multicast_socket, sys.stdin], [], []
            )
            for source in read:
                if source == sys.stdin:
                    message = sys.stdin.readline()
                    if message[:3] == "<Q>":
                        quit = True
                        break
                    elif message[:3] == "<U>":
                        data = f"{nickname};{message[3:]}"
                        udp_socket.sendto(bytearray(data.encode("utf-8")), (HOST, PORT))
                    elif message[:3] == "<M>":
                        data = f"{nickname};{message[3:]}"
                        udp_multicast_socket.sendto(
                            bytearray(data.encode("utf-8")),
                            (MULTICAST_ADDRESS, MULTICAST_PORT),
                        )
                    else:
                        data = f"{nickname};{message}"
                        tcp_socket.sendall(bytearray(data.encode("utf-8")))
                else:
                    data = None
                    if source == tcp_socket:
                        data = str(tcp_socket.recv(1024), "utf-8")
                    elif source == udp_socket:
                        data = str(udp_socket.recv(1024), "utf-8")
                    else:  # source == udp_multicast_socket
                        data = str(udp_multicast_socket.recv(1024), "utf-8")

                    delimiter_position: int = data.find(";")
                    sender_nickname, message = (
                        data[:delimiter_position],
                        data[(delimiter_position + 1) :],
                    )
                    print(f"{sender_nickname}\n{message}\n")
