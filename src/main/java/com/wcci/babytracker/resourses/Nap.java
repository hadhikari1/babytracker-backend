package com.wcci.babytracker.resourses;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.wcci.babytracker.util.Common;
import com.wcci.babytracker.util.modal.TimeDuration;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Embeddable
public class Nap {
    private String typeOfSleep;
    private LocalDateTime sleptFrom;
    private LocalDateTime sleptTo;

    public Nap(String typeOfSleep, LocalDateTime sleptFrom, LocalDateTime sleptTo) {
        this.typeOfSleep = typeOfSleep;
        this.sleptFrom = sleptFrom;
        this.sleptTo = sleptTo;
    }

    public Nap() {
    }

    public String getTypeOfSleep() {
        return typeOfSleep;
    }

    public void setTypeOfSleep(String typeOfSleep) {
        this.typeOfSleep = typeOfSleep;
    }

    public LocalDateTime getSleptFrom() {
        return sleptFrom;
    }

    public void setSleptFrom(LocalDateTime sleptFrom) {
        this.sleptFrom = sleptFrom;
    }

    public LocalDateTime getSleptTo() {
        return sleptTo;
    }

    public void setSleptTo(LocalDateTime sleptTo) {
        this.sleptTo = sleptTo;
    }

    @Transient // this data won't save in database
    @ApiModelProperty(hidden = true) // this will hide json property in Swagger
    @JsonGetter(value = "timeDuration") // since this is transient property method,
    // we need to pass json value in order to get on json when you call API.
    public TimeDuration getTimeDuration(){
        Common common = new Common();
        return common.timeDurationFromNow(this.sleptFrom);
    }
}
