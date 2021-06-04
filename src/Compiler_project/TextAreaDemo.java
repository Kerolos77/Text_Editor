/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compiler_project;

/**
 *
 * @author abokh
 */
import javax.swing.*;
import java.util.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import javax.swing.text.BadLocationException;
import javax.swing.GroupLayout.*;
 
 
public class TextAreaDemo extends JFrame implements DocumentListener {

    private static String COMMIT_ACTION = "commit";
    private static enum Mode { INSERT, COMPLETION };
    private  ArrayList<String> words;
    private Mode mode = Mode.INSERT;
     
    
    Editor e = new Editor();
    public TextAreaDemo() {
        e.t.getDocument().addDocumentListener(this);
        
        InputMap im = e.t.getInputMap();
        ActionMap am = e.t.getActionMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), COMMIT_ACTION);
        am.put(COMMIT_ACTION, new CommitAction());
         
        words = new ArrayList<String>();
        words.add("Omw");
        words.add("SIMww");
        words.add("Chji");
        words.add("Seriestl");
        words.add("IMwf");
        words.add("SIMwf");
        words.add("NOReturn");
        words.add("RepeatWhen");
        words.add("Reiterate");
        words.add("GetBack");
        words.add("OutLoop");
        words.add("Loli");
        words.add("Yesif-Otherwise");
        words.add("Include");
        words.sort(null);
    }
    
     
    // Listener methods
     
    public void changedUpdate(DocumentEvent ev) {
    }
     
    public void removeUpdate(DocumentEvent ev) {
    }
     
    public void insertUpdate(DocumentEvent ev) {
        if (ev.getLength() != 1) {
            return;
        }
         
        int pos = ev.getOffset();
        String content = null;
        try {
            content = e.t.getText(0, pos + 1);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
         
        // Find where the word starts
        int w;
        for (w = pos; w >= 0; w--) {
            if (!Character.isLetter(content.charAt(w))) {
                break;
            }
        }
        if (pos - w < 1) {
            // Too few chars
            return;
        }
         
        String prefix = content.substring(w + 1);
        int n = Collections.binarySearch(words, prefix);
        if (n < 0 && -n <= words.size()) {
            String match = words.get(-n - 1);
            if (match.startsWith(prefix)) {
                // A completion is found
                String completion = match.substring(pos - w);
                // We cannot modify Document from within notification,
                // so we submit a task that does the change later
                SwingUtilities.invokeLater(
                        new CompletionTask(completion, pos + 1));
            }
        } else {
            // Nothing found
            mode = Mode.INSERT;
        }
    }
     
    private class CompletionTask implements Runnable {
        String completion;
        int position;
         
        CompletionTask(String completion, int position) {
            this.completion = completion;
            this.position = position;
        }
         
        public void run() {
            e.t.insert(completion, position);
            e.t.setCaretPosition(position + completion.length());
            e.t.moveCaretPosition(position);
            mode = Mode.COMPLETION;
        }
    }
     
    private class CommitAction extends AbstractAction {
        public void actionPerformed(ActionEvent ev) {
            if (mode == Mode.COMPLETION) {
                int pos = e.t.getSelectionEnd();
                e.t.insert(" ", pos);
                e.t.setCaretPosition(pos + 1);
                mode = Mode.INSERT;
            } else {
                e.t.replaceSelection("\n");
            }
        }
    }
     
}
