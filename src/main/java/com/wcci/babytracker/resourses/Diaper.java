package com.wcci.babytracker.resourses;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wcci.babytracker.util.Common;
import com.wcci.babytracker.util.modal.TimeDuration;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Embeddable
public class Diaper {
    private String stateOfDiaperChanged;
    private LocalDateTime dateTime;

    public Diaper(String stateOfDiaperChanged, LocalDateTime dateTime) {
        this.stateOfDiaperChanged = stateOfDiaperChanged;
        this.dateTime = dateTime;
    }

    public Diaper() {
    }

    public String getStateOfDiaperChanged() {
        return stateOfDiaperChanged;
    }

    public void setStateOfDiaperChanged(String stateOfDiaperChanged) {
        this.stateOfDiaperChanged = stateOfDiaperChanged;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
