package controllers;

import models.view.HelpModel;
import views.HelpView;

import java.awt.*;

public class HelpController extends Control<HelpModel, HelpView> {
    public HelpController(HelpModel helpModel) {
        this.model = helpModel;
    }

    public Image getBackgroundImage() {
        return this.model.imageIcon().getImage();
    }
}
