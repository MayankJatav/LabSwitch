# Import socket module 
import socket			 

# Create a socket object 
s = socket.socket()		 

# Define the port on which you want to connect 
port = 80				

# connect to the server on local computer 
s.connect(('127.0.0.1', port)) 
while True:
    print(s.recv(1024))
# receive data from the server 
'''while True:
    print(s.recv(1024) )'''
# close the connection 
s.close()	 
