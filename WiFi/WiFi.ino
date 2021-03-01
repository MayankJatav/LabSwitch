#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>

/*Put your SSID & Password*/
const char* ssid = "Why Fi?";  // Enter SSID here
const char* password = "chyshy..";  //Enter Password here

ESP8266WebServer server(80);

//uint8_t LEDpin = LED_BUILTIN;
//bool LEDstatus = LOW;
String response;

void setup() {
  Serial.begin(115200);
  Serial.setTimeout(5000);
  delay(100);
  //pinMode(LEDpin, OUTPUT);

  Serial.println("Connecting to ");
  Serial.println(ssid);

  //connect to your local wi-fi network
  WiFi.begin(ssid, password);

  //check wi-fi is connected to wi-fi network
  while (WiFi.status() != WL_CONNECTED) {
  delay(1000);
  Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected..!");                                          
  Serial.print("Got IP: ");  Serial.println(WiFi.localIP());

  

  server.on("/login", Login);
  server.on("/generate", Generate);
  server.on("/remove", Remove);
  server.on("/edit",Edit);
  server.on("/power",Power);
  server.on("/switch",Switch);
  //server.onNotFound(handle_NotFound);

  server.begin();
  Serial.println("HTTP server started");
}
void loop() {
  server.handleClient();
  server.send(200,"text/plain",response);
  response="";
  /*if(LEDstatus)
  digitalWrite(LEDpin, HIGH);
  else
  digitalWrite(LEDpin, LOW);*/
}

void Login()
{
  //Serial.println("Login"); //server.send(404, "text/plain", "Ok");
  String uname=server.arg("uname");
  String pass=server.arg("pass");
  String type=server.arg("type");
  Serial.println("1 "+uname+" "+pass+" "+type);
  
  response=Serial.readString();
  
}
void Generate()
{
  Serial.println("Generate ID and Password"); server.send(404, "text/plain", "Ok");
}
void Remove()
{
  Serial.println("Remove User"); server.send(404, "text/plain", "Ok");
}
void Edit()
{
  Serial.println("Edit Details"); server.send(404, "text/plain", "Ok");
}
void Power()
{
  Serial.println("Power"); server.send(404, "text/plain", "Ok");
}
void Switch()
{
  Serial.println("Switch"); server.send(404, "text/plain", "Ok");
}
/*void handle_OnConnect() {
  LEDstatus = LOW;
  server.send(200, "text/html", SendHTML(false)); 
}

void handle_ledon() {
  LEDstatus = HIGH;
  server.send(200, "text/html", SendHTML(true)); 
}

void handle_ledoff() {
  LEDstatus = LOW;
  server.send(200, "text/html", SendHTML(false)); 
}

void handle_NotFound(){
  server.send(404, "text/plain", "Not found");
}

String SendHTML(uint8_t led){
  String ptr = "<!DOCTYPE html>\n";
  ptr +="<html>\n";
  ptr +="<head>\n";
  ptr +="<title>LED Control</title>\n";
  ptr +="</head>\n";
  ptr +="<body>\n";
  ptr +="<h1>LED</h1>\n";
  ptr +="<p>Click to switch LED on and off.</p>\n";
  ptr +="<form method=\"get\">\n";
  if(led)
  ptr +="<input type=\"button\" value=\"LED OFF\" onclick=\"window.location.href='/ledoff'\">\n";
  else
  ptr +="<input type=\"button\" value=\"LED ON\" onclick=\"window.location.href='/ledon'\">\n";
  ptr +="</form>\n";
  ptr +="</body>\n";
  ptr +="</html>\n";
  return ptr;
}*/
