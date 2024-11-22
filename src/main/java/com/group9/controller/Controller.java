package com.group9.controller;

import com.group9.model.Model;
import com.group9.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        /*// Attach listeners
        view.addIncrementListener(new IncrementListener());
        view.addDecrementListener(new DecrementListener());

        // Initialize the view with the current model state
        view.setCount(model.getCount());*/
    }

    class IncrementListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.incrementCount();
            //view.setCount(model.getCount());
        }
    }

    class DecrementListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.decrementCount();
            //view.setCount(model.getCount());
        }
    }
}
