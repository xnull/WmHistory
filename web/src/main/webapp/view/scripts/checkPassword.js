//Meter update function 
function meterUpdate(e) {
    var score = strengthMeasure(e.value), 
        desc = [ "", "Вэри бэд", "Плохой пароль", "Средненько", "Средненько", "Норм", "Потом вспомнишь?" ],
        meter = $("$meter"), 
        meterWidget = zk.Widget.$(meter);
 
    switch (score) {
        case 1:
        case 2:
            meterWidget.setSclass("meter meter-red");
            break;
        case 3:
        case 4:
            meterWidget.setSclass("meter meter-orange");
            break;
        case 5:
        case 6:
            meterWidget.setSclass("meter meter-green");
            break;
        default:
            meterWidget.setSclass("meter");
    }
     
    zk.Widget.$($(".meter-inner")).setWidth(score * meter.width() / desc.length + "px");

    zk.Widget.$("$msg").setValue(desc[score]);
    zk.Widget.$("$passLevel").setValue(score);
    
}
 
/* Simple Rule */
function strengthMeasure(text) {
    var score = 0;
    if (text.length > 0)
        score++;
 
    if (text.length > 6)
        score++;
 
    if ((text.match(/[a-z]/)) && (text.match(/[A-Z]/)))
        score++;
 
    if (text.match(/\d+/))
        score++;
 
    if (text.match(/.[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/))
        score++;
 
    if (text.length > 12)
        score++;
 
    if (text.length == 0)
        score = 0;
 
    return score;
}

function enableBtn(e){
	var level = zk.Widget.$("$passLevel").getValue();
    if (level > 3 && zk.Widget.$("$pwd1").getValue()==e.value) {
    	zk.Widget.$("$submitBtn").setDisabled(false);
    }
    else{
    	zk.Widget.$("$submitBtn").setDisabled(true);
    }
}