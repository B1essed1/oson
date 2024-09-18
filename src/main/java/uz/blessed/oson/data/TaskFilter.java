package uz.blessed.oson.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uz.blessed.oson.enums.STATUS;

import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data

public class TaskFilter implements Serializable {
    private String title;
    private String dueDate;
    private Integer page;
    private Integer pageSize;
    private STATUS status;

    @JsonIgnore
    private LocalDateTime dueLocalDateTime;


    @JsonIgnore
    public Pageable getPageable(){
        if (getPage()==null){
            setPage(0);
        }
        if (getPageSize()==null){
            setPageSize(10000);
        }
        return PageRequest.of(getPage(), getPageSize());
    }
}
