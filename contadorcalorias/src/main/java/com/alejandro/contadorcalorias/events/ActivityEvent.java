package com.alejandro.contadorcalorias.events;


import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.alejandro.contadorcalorias.entities.Activity;

    
// This class extends the 'AbstractMongoEventListener' class to use lifecycle events.
@Component
public class ActivityEvent extends AbstractMongoEventListener<Activity> {

    // To do certain actions when an object of the class Activity is saved (created or updated)
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Activity> event) {
        Activity currentActivity = event.getSource(); // get the object that is firing the event.

        currentActivity.setName(currentActivity.getName().trim());
    }
    
}
