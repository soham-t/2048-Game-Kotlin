package com.example.soham.a2048

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var x1:Float = 0.0F
    private var x2:Float = 0.0F
    private var y1:Float = 0.0F
    private var y2:Float = 0.0F
    val MIN_DISTANCE:Int = 150
    private var winner = false
    var score= 0
    private val n1 = mutableListOf<Int>(2,4)
    private val n2 = mutableListOf<Int>(0,1,2,3)
    var mat = Array(4, {IntArray(4)})
    var prev = Array(4, {IntArray(4)})
    var textIds = Array(4,{IntArray(4)})
    var cardIds = Array(4, {IntArray(4)})
    fun mappingGrid(){
        textIds[0][0] = R.id.text1
        textIds[0][1] = R.id.text2
        textIds[0][2] = R.id.text3
        textIds[0][3] = R.id.text4
        textIds[1][0] = R.id.text5
        textIds[1][1] = R.id.text6
        textIds[1][2] = R.id.text7
        textIds[1][3] = R.id.text8
        textIds[2][0] = R.id.text9
        textIds[2][1] = R.id.text10
        textIds[2][2] = R.id.text11
        textIds[2][3] = R.id.text12
        textIds[3][0] = R.id.text13
        textIds[3][1] = R.id.text14
        textIds[3][2] = R.id.text15
        textIds[3][3] = R.id.text16
        cardIds[0][0] = R.id.Grid1
        cardIds[0][1] = R.id.Grid2
        cardIds[0][2] = R.id.Grid3
        cardIds[0][3] = R.id.Grid4
        cardIds[1][0] = R.id.Grid5
        cardIds[1][1] = R.id.Grid6
        cardIds[1][2] = R.id.Grid7
        cardIds[1][3] = R.id.Grid8
        cardIds[2][0] = R.id.Grid9
        cardIds[2][1] = R.id.Grid10
        cardIds[2][2] = R.id.Grid11
        cardIds[2][3] = R.id.Grid12
        cardIds[3][0] = R.id.Grid13
        cardIds[3][1] = R.id.Grid14
        cardIds[3][2] = R.id.Grid15
        cardIds[3][3] = R.id.Grid16
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playGameInit()
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //Toast.makeText(applicationContext,"Entered onTouch",Toast.LENGTH_SHORT).show()
        return when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                this.x1 = event?.getX()
                this.y1 = event?.getY()
                true
            }
            MotionEvent.ACTION_UP -> {
                x2 = event?.getX()
                y2 = event?.getY()
                var deltax = x2-x1
                var deltay = y2-y1
                if(Math.abs(deltax)>MIN_DISTANCE && x2>x1){
                    /*action = 1*/
                    //Toast.makeText(applicationContext,"Action 1",Toast.LENGTH_LONG)
                    onSwipeRight()
                }
                else if(Math.abs(deltax)>MIN_DISTANCE && x2<x1){
                    /*action = 2
                    Toast.makeText(applicationContext,"Action 2",Toast.LENGTH_LONG)*/
                    onSwipeLeft()
                }
                else if(Math.abs(deltay)>MIN_DISTANCE && y2>y1){
                    /*action = 3
                    Toast.makeText(applicationContext,"Action 3",Toast.LENGTH_LONG)*/
                    onSwipeDown()
                }
                else if(Math.abs(deltay)>MIN_DISTANCE && y2<y1){
                    /*action = 4
                    Toast.makeText(applicationContext,"Action 4",Toast.LENGTH_LONG)*/
                    onSwipeUp()
                }
                true
            }
            else -> return super.onTouchEvent(event)
        }
    }
    fun showValues(){
        for(i in 0..3){
            for(j in 0..3){
                var t:TextView = findViewById(textIds[i][j])
                var c:CardView = findViewById(cardIds[i][j])
                if(mat[i][j]==0){
                    t.setText("")
                    c.setCardBackgroundColor(resources.getColorStateList(R.color.tint0))
                }
                else{
                    t.setText(mat[i][j].toString())
                    if(mat[i][j]==2)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint2))
                    }
                    else if (mat[i][j]==4)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint4))
                    }
                    else if (mat[i][j]==8)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint8))
                    }
                    else if (mat[i][j]==16)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint16))
                    }
                    else if (mat[i][j]==32)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint32))
                    }
                    else if (mat[i][j]==64)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint64))
                    }
                    else if (mat[i][j]==128)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint128))
                    }
                    else if (mat[i][j]==256)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint256))
                    }
                    else if (mat[i][j]==512)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint512))
                    }
                    else if (mat[i][j]==1024)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint1024))
                    }
                    else if (mat[i][j]==2048)
                    {
                        c.setCardBackgroundColor(resources.getColorStateList(R.color.tint2048))
                    }
                }
            }
        }
    }
    fun playGameInit(){
        clearGrid()
        mappingGrid()
        var init1 = randomNumberGen(n1)
        var init2 = randomNumberGen(n1)
        var pos1x = randomNumberGen(n2)
        var pos1y = randomNumberGen(n2)
        var pos2x = randomNumberGen(n2)
        var pos2y = randomNumberGen(n2)
        mat[pos1x][pos1y] = init1
        mat[pos2x][pos2y] = init2
        showValues()
        scoreUpdate()
    }
    fun randomNumberGen (x:MutableList<Int>):Int {
        var d = x.shuffled().first()
        return d
    }
    fun insertNum () :Boolean{
        var init1 = randomNumberGen(n1)
        var pos1x = randomNumberGen(n2)
        var pos1y = randomNumberGen(n2)
        if(mat[pos1x][pos1y]==0){
        mat[pos1x][pos1y] = init1
        var t1:TextView = findViewById(textIds[pos1x][pos1y])
        t1.setText(init1.toString())
            return true}
        else{
            return false
        }
    }
    fun clearGrid(){
        for (i in 0..3){
            for(j in 0..3){
                mat[i][j]=0
            }
        }
    }
    fun slideDown(){
        var k = 3
        for(j in 0..3){
            for (i in 3 downTo 0){
                if(mat[i][j]!=0){
                    mat[k][j]=mat[i][j]
                    k-=1
                }
            }
            if(k>=0) {
                for (i in k downTo 0) {
                    mat[i][j] = 0
                }
            }
            k=3
        }
    }
    fun scoreUpdate(){
        var t:TextView = findViewById(R.id.score1)
        t.setText("Score:$score")
    }
    fun onSwipeDown(){
        //Toast.makeText(applicationContext,"Brodo",Toast.LENGTH_LONG)
        for (i in 0..3){
            for (j in 0..3){
                prev[i][j] = mat[i][j]
            }
        }
        slideDown()
        for(j in 0..3){
            for(i in 3 downTo 1){
                if(mat[i][j]==mat[i-1][j]) {
                    mat[i][j] += mat[i - 1][j]
                    score+= mat[i][j]
                    if (mat[i][j]==2048)
                        winner=true
                    mat[i - 1][j] = 0
                }
            }
        }
        slideDown()
        var count = 0
        for(i in 0..3){
            for (j in 0..3){
                if (prev[i][j]==mat[i][j]){
                    count+=1
                }
            }
        }
        //Toast.makeText(applicationContext,"count = $count",Toast.LENGTH_SHORT).show()
        if(count<16){
            insertNumbers()
        }
        else if(count==16){
            if(gameOver()){
                var i1 = Intent(this, OverActivity::class.java)
                i1.putExtra("Score",score.toString())
                startActivity(i1)
            }
        }
        //Toast.makeText(applicationContext,"${mat[0][0]},${mat[0][1]},${mat[0][2]},${mat[0][3]},${mat[1][0]},${mat[1][1]},${mat[1][2]},${mat[1][3]},${mat[2][0]},${mat[2][1]},${mat[2][2]},${mat[2][3]},${mat[3][0]},${mat[3][1]},${mat[3][2]},${mat[3][3]}",Toast.LENGTH_LONG).show()
        showValues()
        scoreUpdate()
        if(winner){
            var i2 = Intent(this,WinnerActivity::class.java)
            i2.putExtra("Score",score.toString())
            startActivity(i2)
        }
    }
    fun insertNumbers(){
        var b = false
        while(b==false){
            b = insertNum()
        }
    }
    fun slideUp(){
        var k=0
        for (j in 0..3){
            for (i in 0..3){
                if(mat[i][j]!=0){
                    mat[k][j] = mat[i][j]
                    k+=1
                }
            }
            if (k<=3) {
                for (i in k..3) {
                    mat[i][j] = 0
                }
            }
            k=0
        }
    }
    fun onSwipeUp(){
        for (i in 0..3){
            for (j in 0..3){
                prev[i][j] = mat[i][j]
            }
        }
        slideUp()
        for(j in 0..3){
            for(i in 0..2){
                if(mat[i][j]==mat[i+1][j]) {
                    mat[i][j] += mat[i + 1][j]
                    score+= mat[i][j]
                    if (mat[i][j]==2048)
                        winner=true
                    mat[i+1][j] = 0
                }
            }
        }
        slideUp()
        var count = 0
        for(i in 0..3){
            for (j in 0..3){
                if (prev[i][j]==mat[i][j]){
                    count+=1
                }
            }
        }
        //Toast.makeText(applicationContext,"count = $count",Toast.LENGTH_SHORT).show()
        if(count<16){
            insertNumbers()
        }
        else if(count==16){
            if(gameOver()){
                var i1 = Intent(this, OverActivity::class.java)
                i1.putExtra("Score",score.toString())
                startActivity(i1)
            }
        }
        //Toast.makeText(applicationContext,"${mat[0][0]},${mat[0][1]},${mat[0][2]},${mat[0][3]},${mat[1][0]},${mat[1][1]},${mat[1][2]},${mat[1][3]},${mat[2][0]},${mat[2][1]},${mat[2][2]},${mat[2][3]},${mat[3][0]},${mat[3][1]},${mat[3][2]},${mat[3][3]}",Toast.LENGTH_LONG).show()
        showValues()
        scoreUpdate()
        if(winner){
            var i2 = Intent(this,WinnerActivity::class.java)
            i2.putExtra("Score",score.toString())
            startActivity(i2)
        }
    }
    fun slideLeft(){
        var k =0
        for (i in 0..3){
            for (j in 0..3){
                if(mat[i][j]!=0){
                    mat[i][k]=mat[i][j]
                    k+=1
                }
            }
            if(k<=3){
                for(j in k..3){
                    mat[i][j]=0
                }
            }
            k=0
        }
    }
    fun onSwipeLeft(){
        for (i in 0..3){
            for (j in 0..3){
                prev[i][j] = mat[i][j]
            }
        }
        slideLeft()
        for(i in 0..3){
            for(j in 0..2){
                if(mat[i][j]==mat[i][j+1]) {
                    mat[i][j] += mat[i][j + 1]
                    score+= mat[i][j]
                    if (mat[i][j]==2048)
                        winner=true
                    mat[i][j + 1] = 0
                }
            }
        }
        slideLeft()
        var count = 0
        for(i in 0..3){
            for (j in 0..3){
                if (prev[i][j]==mat[i][j]){
                    count+=1
                }
            }
        }
        //Toast.makeText(applicationContext,"count = $count",Toast.LENGTH_SHORT).show()
        if(count<16){
            insertNumbers()
        }
        else if(count==16){
            if(gameOver()){
                var i1 = Intent(this, OverActivity::class.java)
                i1.putExtra("Score",score.toString())
                startActivity(i1)
            }
        }
        //Toast.makeText(applicationContext,"${mat[0][0]},${mat[0][1]},${mat[0][2]},${mat[0][3]},${mat[1][0]},${mat[1][1]},${mat[1][2]},${mat[1][3]},${mat[2][0]},${mat[2][1]},${mat[2][2]},${mat[2][3]},${mat[3][0]},${mat[3][1]},${mat[3][2]},${mat[3][3]}",Toast.LENGTH_LONG).show()
        showValues()
        scoreUpdate()
        if(winner){
            var i2 = Intent(this,WinnerActivity::class.java)
            i2.putExtra("Score",score.toString())
            startActivity(i2)
        }
    }
    fun gameOver():Boolean{
        for (i in 0..3){
            for (j in 0..3){
                prev[i][j] = mat[i][j]
                if(mat[i][j]==0){
                    return false
                }
            }
        }
        slideUp()
        slideDown()
        slideLeft()
        slideRight()
        var count = 0
        for (i in 0..3){
            for (j in 0..3){
                if(prev[i][j]==mat[i][j]){
                    count+=1
                }
            }
        }
        if(count==16){
            for (i in 0..3){
                for (j in 0..3){
                    mat[i][j] = prev[i][j]
                }
            }
            return true
        }
        else{
            for (i in 0..3){
                for (j in 0..3){
                    mat[i][j] = prev[i][j]
                }
            }
            return false
        }
    }
    fun slideRight(){
        var k =3
        for (i in 0..3){
            for (j in 3 downTo 0){
                if(mat[i][j]!=0){
                    mat[i][k]=mat[i][j]
                    k-=1
                }
            }
            if(k>=0){
                for (j in k downTo 0){
                    mat[i][j]=0
                }
            }
            k=3
        }
    }
    fun onSwipeRight(){
        for (i in 0..3){
            for (j in 0..3){
                prev[i][j] = mat[i][j]
            }
        }
        slideRight()
        for(i in 0..3){
            for(j in 3 downTo 1){
                if(mat[i][j]==mat[i][j-1]) {
                    mat[i][j] += mat[i][j - 1]
                    score+= mat[i][j]
                    if (mat[i][j]==2048)
                        winner=true
                    mat[i][j - 1] = 0
                }
            }
        }
        slideRight()
        var count = 0
        for(i in 0..3){
            for (j in 0..3){
                if (prev[i][j]==mat[i][j]){
                    count+=1
                }
            }
        }
        //Toast.makeText(applicationContext,"count = $count",Toast.LENGTH_SHORT).show()
        if(count<16){
            insertNumbers()
        }
        else if(count==16){
            if(gameOver()){
                var i1 = Intent(this, OverActivity::class.java)
                i1.putExtra("Score",score.toString())
                startActivity(i1)
            }
        }
        //Toast.makeText(applicationContext,"${mat[0][0]},${mat[0][1]},${mat[0][2]},${mat[0][3]},${mat[1][0]},${mat[1][1]},${mat[1][2]},${mat[1][3]},${mat[2][0]},${mat[2][1]},${mat[2][2]},${mat[2][3]},${mat[3][0]},${mat[3][1]},${mat[3][2]},${mat[3][3]}",Toast.LENGTH_LONG).show()
        showValues()
        scoreUpdate()
        if(winner){
            var i2 = Intent(this,WinnerActivity::class.java)
            i2.putExtra("Score",score.toString())
            startActivity(i2)
        }
    }
}
