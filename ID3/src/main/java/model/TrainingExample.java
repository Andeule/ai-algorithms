package model;

import com.sun.istack.internal.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TrainingExample {

    private List<TrainingExampleRow> attributes;
    private TrainingExampleHeadline headline;


    public TrainingExample(@NonNull TrainingExampleHeadline headline, @Nullable List<TrainingExampleRow> attributes) {
        this.headline = headline;
        if (attributes != null) {
            this.attributes = attributes;
        } else {
            this.attributes = new ArrayList<>();
        }
    }

}
