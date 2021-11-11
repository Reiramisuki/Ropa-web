package gadgets;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

    @Named
@ViewScoped
public class ClockView implements Serializable {

    private LocalDateTime dateTime;


    @PostConstruct
    public void init() {
        dateTime = LocalDateTime.now().plusYears(37).plusMonths(3).plusHours(4);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

