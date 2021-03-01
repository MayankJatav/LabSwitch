
#include<stdio.h>
#include<strings.h>
int main()
{
    char s[]="1 user pass admin";
    char uname[10],pass[10],type[10];
    sscanf(s,"%*d %s %s %s",uname,pass,type);
    printf("%s %s %s dsjfal",uname,pass,type);
}
