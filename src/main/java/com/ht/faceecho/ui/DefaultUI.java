package com.ht.faceecho.ui;

import com.google.gson.Gson;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Getter
public class DefaultUI {
  private final Gson gson = new Gson();
  private JTextArea logConsole;
  private final SimpleDateFormat yyyyMMdd_hhmmss = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");

  public void appendLog(final String prefix, final Object obj) {
    this.appendLog(prefix + this.gson.toJson(obj));
  }

  public void appendLog(final String text) {
    this.logConsole.append(this.yyyyMMdd_hhmmss.format(new Date()) + "  " + text + "\n");
    this.logConsole.setCaretPosition(this.logConsole.getDocument().getLength());
  }

  @PostConstruct
  public void init() {
    JFrame frame = new JFrame();

    this.logConsole = new JTextArea();
    this.logConsole.setEditable(false);
    this.logConsole.setBounds(100, 100, 800, 300);
    JScrollPane jScrollPane = new JScrollPane(this.logConsole);
    frame.setLayout(new BorderLayout());
    frame.add(jScrollPane, BorderLayout.CENTER);

    frame.setBounds(100, 100, 800, 300);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
