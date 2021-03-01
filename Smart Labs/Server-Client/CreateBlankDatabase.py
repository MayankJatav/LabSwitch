import sqlite3 as sql
db=sql.connect("database.db")
db.execute("create table pcs (sno int primary kEY NOT NULL, ip text not null, name text, lab text, status int not null);")
db.close()
