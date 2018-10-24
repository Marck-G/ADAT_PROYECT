let isVisible = false;
let btnBack = document.getElementById( "arrow" );
btnBack.onclick = hide;
hide();

function hide(){
    let navBar = document.getElementById( "nav" );
    let width = navBar.offsetWidth;
    let icon = document.getElementById("icon");
   if ( isVisible ) {
       navBar.style.left = "0";
       icon.className = " fas fa-times ";
       btnBack.className = "return-btn";
       isVisible = false;
   }else{
       navBar.style.left = "-" + (width + 20) + "px";
       isVisible = true;
       icon.className = " fas fa-bars ";
       btnBack.className = "return-btn-hide";
       
   }
}