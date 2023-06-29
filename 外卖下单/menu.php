<?php

$dish= array(
    array("Surmai Chilli",85),
    array("Sausages",25),
    array("Beef Goulash",90),
    array("Onion Soup",22),
    array("Handi",25),
    array("Pancakes",30),
    array("Beef Steak",60),
    array("Omelet",30),
    array("Sandwich",30),
    array("Mutnd",40),
);
$quantity1=(int)$_POST['menu1'];
$quantity2=(int)$_POST['menu2'];
$quantity3=(int)$_POST['menu3'];
$quantity4=(int)$_POST['menu4'];
$quantity5=(int)$_POST['menu5'];
$quantity6=(int)$_POST['menu6'];
$quantity7=(int)$_POST['menu7'];
$quantity8=(int)$_POST['menu8'];
$quantity9=(int)$_POST['menu9'];
$quantity10=(int)$_POST['menu10'];
$sumarr=array($quantity1*(int)$dish[0][1],$quantity2*(int)$dish[1][1],$quantity3*(int)$dish[2][1],$quantity4*(int)$dish[3][1],$quantity5*(int)$dish[4][1],$quantity6*(int)$dish[5][1],$quantity7*(int)$dish[6][1],$quantity8*(int)$dish[7][1],$quantity9*(int)$dish[8][1],$quantity10*(int)$dish[9][1]);

$dis="0";
$pri="0";


$conn = mysqli_connect("mysql648.db.sakura.ne.jp", "restaurant", "hay113718", "restaurant_113");
function conn(){
if ($conn->connect_error) {
    die("连接失败: " . $conn->connect_error);
} 
}  


if(isset($_POST["menu"])){
$date=date('Y-m-d, H:i:s');
$num = rand(100000000, 999999999);
$conn; 

echo "您好，谢谢惠顾！您的单号是".$num."，在".$date."购买了:";
                        
if($quantity1>0){
    $dis=$dish[0][0];
    $pri=$quantity1*(int)$dish[0][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity1','$pri','$num','$date')");
echo $dis.$quantity1."份!";
}

                          
if($quantity2>0){
    $dis=$dish[1][0];
    $pri=$quantity2*(int)$dish[1][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity2','$pri','$num','$date')");
echo $dis.$quantity2."份!";
}
                         
if($quantity3>0){
    $dis=$dish[2][0];
    $pri=$quantity3*(int)$dish[2][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity3','$pri','$num','$date')");
echo $dis.$quantity3."份!";
}
                            
if($quantity4>0){
    $dis=$dish[3][0];
    $pri=$quantity4*(int)$dish[3][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity4','$pri','$num','$date')");
echo $dis.$quantity4."份!";
}
                         
if($quantity5>0){
    $dis=$dish[4][0];
    $pri=$quantity5*(int)$dish[4][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity5','$pri','$num','$date')");
echo $dis.$quantity5."份!";
}
                            
if($quantity6>0){
    $dis=$dish[5][0];
    $pri=$quantity6*(int)$dish[5][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity6','$pri','$num','$date')");
echo $dis.$quantity6."份!";
}
                           
if($quantity7>0){
    $dis=$dish[6][0];
    $pri=$quantity7*(int)$dish[6][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity7','$pri','$num','$date')");
echo $dis.$quantity7."份!";
}
                           
if($quantity8>0){
    $dis=$dish[7][0];
    $pri=$quantity8*(int)$dish[7][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity8','$pri','$num','$date')");
echo $dis.$quantity8."份!";
}
                           
if($quantity9>0){
    $dis=$dish[8][0];
    $pri=$quantity9*(int)$dish[8][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity9','$pri','$num','$date')");
echo $dis.$quantity9."份!";
}
                            
if($quantity10>0){
    $dis=$dish[9][0];
    $pri=$quantity10*(int)$dish[9][1];
mysqli_query($conn, "insert into menus (dish,quantity,price,num,date) values ('$dis','$quantity10','$pri','$num','$date')");
echo $dis.$quantity10."份!";
}


echo "一共消费".array_sum($sumarr)."元";
}

?>