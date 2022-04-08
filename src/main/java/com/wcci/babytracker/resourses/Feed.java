package com.wcci.babytracker.resourses;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wcci.babytracker.util.Common;
import com.wcci.babytracker.util.modal.TimeDuration;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Embeddable
public class Feed {
    private String feedingMethod;
    private int quantity;
    private LocalDateTime dateTime;

    public Feed(String feedingMethod, int quantity, LocalDateTime dateTime) {
        this.feedingMethod = feedingMethod;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }

    public Feed() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFeedingMethod() {
        return feedingMethod;
    }

    public int getQuantity() {
        return quantity;
    }

    @Transient // this data won't save in database
    @ApiModelProperty(hidden = true) // this will hide json property in Swagger
    @JsonGetter(value = "timeDuration") // since this is transient property method,
    // we need to pass json value in order to get on json when you call API.
    public TimeDuration getTimeDuration(){
        Common common = new Common();
        return common.timeDurationFromNow(this.dateTime);
    }
}
