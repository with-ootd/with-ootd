package com.ootd.with.domain.board;

import com.ootd.with.domain.BaseTimeEntity;
import com.ootd.with.domain.enumtype.StatusType;
import com.ootd.with.domain.post.Post;
import com.ootd.with.web.board.CreateBoardForm;
import com.ootd.with.web.board.UpdateBoardForm;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_name")
    private String name;

    @OneToMany(mappedBy = "board")
    private List<Post> postList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusType statusType;

    @Builder
    public Board(String name, StatusType statusType) {
        this.name = name;
        this.statusType = statusType;
    }

    public void changeStatus(StatusType type) {
        this.statusType = type;
    }

    public static Board createBoard(CreateBoardForm form) {
        Board board = Board.builder()
                .name(form.getName())
                .statusType(form.getStatusType())
                .build();
        return board;
    }

    public void updateBoard(UpdateBoardForm form) {
        if(form.getName() != null) this.name = form.getName();
        if(form.getStatusType() != null) this.statusType = form.getStatusType();
    }
}
