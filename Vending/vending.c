//Implementation of a Mealy Machine
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define N 30
int arr[N];
int sum,k=0;
const char* bev[]={"Tea","Coffee","Tomato Soup","Lemon Tea"};
int currency[]={1,2,5,10};

void terminate();
void vending();
void bselect();
void coinenter();
void change();
int validcoin(int c);

int main(){
printf("\n\n************VENDING MACHINE*************\n");
printf("\n1. Tea	 		Rs 20");
printf("\n2. Coffee		Rs 20");
printf("\n3. Tomato Soup  	Rs 20");
printf("\n4. Lemon Tea		Rs 20");
vending();
}

void terminate(){
printf("\n\n---------STATE : Terminating ----------\n");
char choice;
printf("Do you want to buy another item?: Y(Yes)/N(No)");
scanf(" %c", &choice);
if((choice=='Y')||(choice=='y')){
    sum =0;
    memset(arr, 0, sizeof arr);
    vending();
    }
else if((choice=='n')||(choice=='N')){
    printf("\n\n------------------------\n\n");
    exit(0);
    }
}

void bselect(){
int ch;
printf("\n\n---------STATE : Beverage Selection ----------\n");
printf("Enter 1/2/3/4 to select your beverage: ");
scanf("%d",&ch);
printf("\nHere's your %s! ",bev[ch-1]);
terminate();
}

void change(int s){
printf("\n\n---------STATE : Return Excess Change ----------\n");
sum = sum - arr[k-1];
printf("Here's your coin of value %d back, please enter the exact change. ",arr[k-1]);
coinenter();
}

int validcoin(int c){
int i;
for(i=0;i<(sizeof(currency)/sizeof(currency[0]));i++)
	if(c==currency[i])
		return 1;
printf("Invalid Entry!\nPlease enter a valid coin");
return 0;
}

void coinenter(){
int c;
printf("\n\n---------STATE : Coin Insertion ----------\n");
printf("Enter a coin: ");
scanf("%d",&c);
if(validcoin(c)==0)
    coinenter();
sum+=c;
arr[k++]=c;
printf("You have eneterd an amount of %d",sum);
if(sum>20)
    change(sum);
if(sum==20)
    bselect();
else coinenter();
}



void vending(){
printf("\n\n---------STATE : Machine Initiating ----------\n");
printf("Enter money for your beverage.\n");
coinenter();
}


