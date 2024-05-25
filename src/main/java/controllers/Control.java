package controllers;

import javax.swing.*;

public abstract class Control<M, V extends JPanel> {
    M model;
    V view;
}
