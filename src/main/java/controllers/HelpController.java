package controllers;

import models.view.HelpModel;

import java.awt.*;

public class HelpController extends Control<HelpModel> {
    public HelpController(HelpModel helpModel) {
        this.model = helpModel;
    }

    public Image getBackgroundImage() {
        return this.model.imageIcon().getImage();
    }
}
