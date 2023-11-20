package com.example.myapplication;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CounterActivity extends AppCompatActivity {

    private int count = 0;

    private int incorrectAnswerCount = 0;
    private int inRowCorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Random r = new Random();
        String msg = getIntent().getStringExtra("Difficulty");
        int[] displayNumbers = {0, 0};
        if (msg.equals("Easy")){
            displayNumbers = generateEasy();
        }
        else if (msg.equals("Medium")){
            displayNumbers = generateMedium();
        }
        else if (msg.equals("Hard")){
            displayNumbers = generateHard();
        }
        TextView firstNumber = findViewById(R.id.numberOne);
        firstNumber.setText("   " + displayNumbers[0]);
        TextView secondNumber = findViewById(R.id.numberTwo);
        secondNumber.setText("   " + displayNumbers[1]);
    }

    public int[] generateEasy(){
        boolean difficultNumbers = true;
        Random r = new Random();
        int[] displayNumbers = {0, 0};
        while (difficultNumbers) {
            int firstNum = r.nextInt(90) + 10;
            int secondNum = r.nextInt(90) + 10;
            // Convert the integer to a string
            String firstNumberString = Integer.toString(firstNum);
            String secondNumberString = Integer.toString(secondNum);

            // Iterate through the characters and get the first digit
            char firstDigitFirstNumStr = firstNumberString.charAt(1);
            char secondDigitFirstNumStr = firstNumberString.charAt(0);
            char firstDigitSecondNumStr = secondNumberString.charAt(1);
            char secondDigitSecondNumStr = secondNumberString.charAt(0);

            // Convert the first digit back to an integer if needed
            int firstNumFirst = Character.getNumericValue(firstDigitFirstNumStr);
            int firstNumSecond = Character.getNumericValue(secondDigitFirstNumStr);
            int secondNumFirst = Character.getNumericValue(firstDigitSecondNumStr);
            int secondNumSecond = Character.getNumericValue(secondDigitSecondNumStr);

            //check
            if (((firstNumFirst+secondNumFirst)<=9) && ((firstNumSecond+secondNumSecond)<=9)){
                difficultNumbers = false;
                displayNumbers[0] = firstNum;
                displayNumbers[1] =  secondNum;
            }
        }
        return displayNumbers;
    }

    public int[] generateMedium(){
        boolean difficultNumbers = true;
        Random r = new Random();
        int[] displayNumbers = {0, 0};
        while (difficultNumbers) {
            int firstNum = r.nextInt(90) + 10;
            int secondNum = r.nextInt(90) + 10;
            // Convert the integer to a string
            String firstNumberString = Integer.toString(firstNum);
            String secondNumberString = Integer.toString(secondNum);

            // Iterate through the characters and get the first digit
            char firstDigitFirstNumStr = firstNumberString.charAt(1);
            char secondDigitFirstNumStr = firstNumberString.charAt(0);
            char firstDigitSecondNumStr = secondNumberString.charAt(1);
            char secondDigitSecondNumStr = secondNumberString.charAt(0);

            // Convert the first digit back to an integer if needed
            int firstNumFirst = Character.getNumericValue(firstDigitFirstNumStr);
            int firstNumSecond = Character.getNumericValue(secondDigitFirstNumStr);
            int secondNumFirst = Character.getNumericValue(firstDigitSecondNumStr);
            int secondNumSecond = Character.getNumericValue(secondDigitSecondNumStr);

            //check

            //if the first digit sum is less than or equal to nine
            if ((firstNumFirst+secondNumFirst)<=9){
                //and if the second one is bigger than nine, stop generating
                if ((firstNumSecond+secondNumSecond)>9){
                    difficultNumbers = false;
                    displayNumbers[0] = firstNum;
                    displayNumbers[1] = secondNum;
                }
            }
            //else if the first dig sum is bigger than nine
            else if ((firstNumFirst+secondNumFirst)>9){
                //and if the second dig sum is less than or equal to nine
                if ((firstNumSecond+secondNumSecond)<=9){
                    difficultNumbers = false;
                    displayNumbers[0] = firstNum;
                    displayNumbers[1] = secondNum;
                }
            }
        }
        return displayNumbers;
    }

    public int[] generateHard(){
        boolean difficultNumbers = true;
        Random r = new Random();
        int[] displayNumbers = {0, 0};
        while (difficultNumbers) {
            int firstNum = r.nextInt(90) + 10;
            int secondNum = r.nextInt(90) + 10;
            // Convert the integer to a string
            String firstNumberString = Integer.toString(firstNum);
            String secondNumberString = Integer.toString(secondNum);

            // Iterate through the characters and get the first digit
            char firstDigitFirstNumStr = firstNumberString.charAt(1);
            char secondDigitFirstNumStr = firstNumberString.charAt(0);
            char firstDigitSecondNumStr = secondNumberString.charAt(1);
            char secondDigitSecondNumStr = secondNumberString.charAt(0);

            // Convert the first digit back to an integer if needed
            int firstNumFirst = Character.getNumericValue(firstDigitFirstNumStr);
            int firstNumSecond = Character.getNumericValue(secondDigitFirstNumStr);
            int secondNumFirst = Character.getNumericValue(firstDigitSecondNumStr);
            int secondNumSecond = Character.getNumericValue(secondDigitSecondNumStr);

            //check
            if (((firstNumFirst+secondNumFirst)>9) && ((firstNumSecond+secondNumSecond)>9)){
                difficultNumbers = false;
                displayNumbers[0] = firstNum;
                displayNumbers[1] =  secondNum;
            }
        }
        return displayNumbers;
    }

    public void clearClick(View v){
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (v.getId() == R.id.clear) {
            if (count > 1){
                brokenDownAnswer[whichIndex-count] = ' ';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
                count--;
            }
            else if (count == 1) {
                count--;
                brokenDownAnswer[whichIndex-1] = '?';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void oneClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.one) {
                count++;
                brokenDownAnswer[whichIndex - count] = '1';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void twoClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.two) {
                count++;
                brokenDownAnswer[whichIndex - count] = '2';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void threeClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.three) {
                count++;
                brokenDownAnswer[whichIndex - count] = '3';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void fourClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.four) {
                count++;
                brokenDownAnswer[whichIndex - count] = '4';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void fiveClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.five) {
                count++;
                brokenDownAnswer[whichIndex - count] = '5';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void sixClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.six) {
                count++;
                brokenDownAnswer[whichIndex - count] = '6';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void sevenClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.seven) {
                count++;
                brokenDownAnswer[whichIndex - count] = '7';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void eightClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.eight) {
                count++;
                brokenDownAnswer[whichIndex - count] = '8';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void nineClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.nine) {
                count++;
                brokenDownAnswer[whichIndex - count] = '9';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void zeroClick(View v) {
        TextView userAnswer = findViewById(R.id.answer);
        String fullTextAnswer = userAnswer.getText().toString();
        char[] brokenDownAnswer = fullTextAnswer.toCharArray();
        int whichIndex = brokenDownAnswer.length;
        String displayNumber;
        if (!(count >= 3)) {
            if (v.getId() == R.id.zero) {
                count++;
                brokenDownAnswer[whichIndex - count] = '0';
                displayNumber = new String(brokenDownAnswer);
                userAnswer.setText(displayNumber);
            }
        }
    }

    public void submitClick(View v){
        if (v.getId() == R.id.submit) {
            TextView firstNumber = findViewById(R.id.numberOne);
            String firstNumberString = firstNumber.getText().toString().trim();
            int firstIntegerNumber = Integer.parseInt(firstNumberString);
            TextView secondNumber = findViewById(R.id.numberTwo);
            String secondNumberString = secondNumber.getText().toString().trim();
            int secondIntegerNumber = Integer.parseInt(secondNumberString);
            int realResponse = firstIntegerNumber + secondIntegerNumber;
            TextView userResponse = findViewById(R.id.answer);
            String croppedResponse = userResponse.getText().toString().trim();
            if (!(croppedResponse.equals("?"))) {
                int responseInteger = Integer.parseInt(croppedResponse);
                if (responseInteger == realResponse) {
                    incorrectAnswerCount = 0;
                    inRowCorrect++;
                    if (inRowCorrect > 1) {
                        Toast.makeText(this, "Correct Answer! " + inRowCorrect + " in a row!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Correct Answer!", Toast.LENGTH_LONG).show();
                    }
                    // create an Executor object to run the background task
                    Executor executor = Executors.newSingleThreadExecutor();

                    // Use a lambda expression and pass it to “execute” method
                    executor.execute(() -> {

                        // Sleep for three seconds
                        try {
                            Thread.sleep(3000);
                        } catch (Exception e) {
                        }

                        Random r = new Random();
                        String msg = getIntent().getStringExtra("Difficulty");
                        int[] displayNumbers = {0, 0};
                        if (msg.equals("Easy")){
                            displayNumbers = generateEasy();
                        }
                        else if (msg.equals("Medium")){
                            displayNumbers = generateMedium();
                        }
                        else if (msg.equals("Hard")){
                            displayNumbers = generateHard();
                        }
                        firstNumber.setText("   " + displayNumbers[0]);
                        secondNumber.setText("   " + displayNumbers[1]);
                    });
                } else {
                    incorrectAnswerCount++;
                    if (!(incorrectAnswerCount%3==0)) {
                        Toast.makeText(this, "Incorrect! Try again!", Toast.LENGTH_LONG).show();
                    }
                    if (incorrectAnswerCount%3 == 0) {
                        Toast.makeText(this, "Incorrect! The correct answer is " + realResponse, Toast.LENGTH_LONG).show();
                        inRowCorrect = 0;

                        Executor executor = Executors.newSingleThreadExecutor();

                        // Use a lambda expression and pass it to “execute” method
                        executor.execute(() -> {

                            String responseText = Integer.toString(realResponse);
                            int remainingSpots = 5 - responseText.length();
                            count = responseText.trim().length();
                            userResponse.setText(" ".repeat(remainingSpots) + responseText);
                            userResponse.setTextColor(Color.RED);

                            try { Thread.sleep(3000);}
                            catch (Exception e) {}

                            userResponse.setTextColor(Color.BLUE);
                            Random r = new Random();
                            String msg = getIntent().getStringExtra("Difficulty");
                            int[] displayNumbers = {0, 0};
                            if (msg.equals("Easy")){
                                displayNumbers = generateEasy();
                            }
                            else if (msg.equals("Medium")){
                                displayNumbers = generateMedium();
                            }
                            else if (msg.equals("Hard")){
                                displayNumbers = generateHard();
                            }
                            firstNumber.setText("   " + displayNumbers[0]);
                            secondNumber.setText("   " + displayNumbers[1]);

                        });
                    }
                }
            }
            else {
                Toast.makeText(this, "Enter a valid value please", Toast.LENGTH_LONG).show();
            }
        }
    }
}
