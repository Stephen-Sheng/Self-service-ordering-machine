# A self-service machine for ramen restaurant



## Operation method

You don't need to install this program. Just compile all the java files and run the Test.java file.

Compile: please input "javac *.java" in command line.

Run: please input "java Test" in command line.

Note: please place the folder "Files" in the same directory with all the java files.



## Program document overview

This program includes five aspects: administrator, user, entity, GUI control and file management。Administrator and user include GUI files of manager and client，on behalf of boundary classes；Entity represents entity classes，including four kinds of entities: user，menu, order and each line of menu called "oneLine"；GUI control is a set of classes that control between GUI and entity, they are login control, payment control and administrator control. File management includes all the classes of reading and writing txt files. GUI control and file management represent control classes.

Administrator: FunctionPage.java, ChangePrice.java, ModifyMenu.java, OrderList.java, RankSpiciness.java, ViewReport.java, AddOptions.java;

User: Test.java, Welcome.java, OrderPageOne.java, OrderPageTwoAddons.java, EatinOrTakeaway.java, Login.java, Register.java, ViewAccount.java, ModifyInformation.java, ViewHistoryOrder.java, CheckOrder.java, PaymentPage.java, PaymentPage2.java, Ticket.java;

Entity: User.java, Menu.java, Order.java, OneLine.java;

GUI control: LoginControl.java, PaymentPageControl.java, AdministratorControl.java;

File management: UserInfoManagement.java, MenuManagement.java, OrderManagement.java, OrderCollectionManagement.java.

All the txt files and pictures are placed in a folder called File: userInfo.txt, menu.txt, order.txt, orderCollection.txt, totoro.png.

