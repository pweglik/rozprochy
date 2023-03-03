import socket;

serverIP = "127.0.0.1"
serverPort = 9008
buff = []

print('PYTHON UDP CLIENT')
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.connect((serverIP, serverPort))

msg_bytes = (2).to_bytes(4, byteorder='little')
print('Send: '  + str(int.from_bytes(msg_bytes, byteorder='little'))) 
client.send(msg_bytes)


data = client.recv(1024)

print('Received from server: ' + str(int.from_bytes(data, byteorder='little'))) 
