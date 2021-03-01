import socket
import sqlite3 as sql
import threading
import sys

class Server:
    global port
    global s

    def connection():
        try:
            global port, s
            port = 80
            s=socket.socket()
            s.bind(('',port))
            s.listen()
            print("I am Ready")
            while True:
                conn, address = s.accept()
                print(address[0])
                if not Server.setpcon(address[0]):
                    print(address[0]+" is not present in database")
                    conn.send("You are not present in databsae")
        except socket.error as msg:
            print("Connection error: "+str(msg))

    def setpcon(address):
        #try:
            db = sql.connect("database.db")
            dbcursor = db.execute("select ip from pcs where ip ='"+str(address)+"'")
            y=""
            for x in dbcursor:
                y=x
            if(address in y):
                db.execute("update pcs set status=1 where ip='"+str(address)+"'")
                db.commit()
                return True
            else:
                return False
            
        #except


    # Main Starts Here
    
    db = sql.connect("database.db")

    # Set all PCS status to 0 
    db.execute("update pcs set status=0 where true")
    db.commit()

    # Create Threads
    t1 = threading.Thread(target=connection)

    # Run Thread
    t1.start()
