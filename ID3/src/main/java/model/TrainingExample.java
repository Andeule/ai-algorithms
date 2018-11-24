package model;

import com.sun.istack.internal.Nullable;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TrainingExample {

    private List<TrainingExampleRow> attributes = new ArrayList<>();
    ;
    private TrainingExampleHeadline headline;


    public TrainingExample(TrainingExampleHeadline headline, List<TrainingExampleRow> attributes) {
        this.headline = headline;
        this.attributes = attributes;
    }

}
