package com.mycompany.foodsearchingforwinter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**This class implements keylistener which It listens to the rest of the user's
 * input from the keyboard, which includes variables representing the four directions
 * of up, down, left and right. The movement of the character is controlled by pressing or
 * releasing the four keys representing the up, down, left and right.
 */
public class Control implements KeyListener {
    public boolean isUp, isDown, isRight, isLeft, isIdle;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**This method will move immediately character  after user press the key which reperenst direction
     * it take the information which get from the keyboard
     * @param e the Direction Key entered by user from keyboard
     * @see The character move after pressing the key
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            isUp = true;
        }
        if (code == KeyEvent.VK_S){
            isDown = true;
        }
        if (code == KeyEvent.VK_A){
            isLeft = true;
        }
        if (code == KeyEvent.VK_D){
            isRight = true;
        }
    }

    /**This method will stop character action after user release the key, it take the
     * information which get from the keyboard
     * @param e the Direction Key entered by user from keyboard
     * @see The character stop action after releasing the key
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            isUp = false;
        }
        if (code == KeyEvent.VK_S){
            isDown = false;
        }
        if (code == KeyEvent.VK_A){
            isLeft = false;
        }
        if (code == KeyEvent.VK_D){
            isRight = false;
        }
    }
}
