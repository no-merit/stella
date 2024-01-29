package com.stella.stella.comment.service;

import com.stella.stella.board.repository.BoardRepository;
import com.stella.stella.comment.dto.MultcommentDeleteRequestDto;
import com.stella.stella.comment.dto.MulticommentCreateRequestDto;
import com.stella.stella.comment.entity.Comment;
import com.stella.stella.comment.entity.MultiComment;
import com.stella.stella.comment.repository.CommentRepository;
import com.stella.stella.comment.repository.MulticommentRepository;
import com.stella.stella.common.exception.CustomException;
import com.stella.stella.common.exception.CustomExceptionStatus;
import com.stella.stella.member.entity.Member;
import com.stella.stella.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MulticommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final MulticommentRepository multicommentRepository;

    public void addMultiComment(MulticommentCreateRequestDto dto){
        Comment comment = commentRepository.findByCommentIndex(dto.getCommentIndex()).orElseThrow(()->new CustomException(CustomExceptionStatus.COMMENTID_INVALID));
        Member member = memberRepository.findByMemberIndex(dto.getMemberIndex()).orElseThrow(()->new CustomException(CustomExceptionStatus.MEMBERID_INVALID));

        MultiComment multiComment = MultiComment.builder()
                .multiCommentContent(dto.getCommentContent())
                .comment(comment)
                .member(member).build();

        multicommentRepository.save(multiComment);
    }

    public void removeMultiComment(MultcommentDeleteRequestDto dto){
        MultiComment multiComment = multicommentRepository.findByMultiCommentIndex(dto.getMultcommentIndex()).orElseThrow(()->new CustomException(CustomExceptionStatus.MULTICOMMENTINDEX_INVALID));
        if(multiComment.getMember().getMemberIndex()==dto.getMemberIndex()) {
            multicommentRepository.delete(multiComment);
        }else{
            throw new CustomException(CustomExceptionStatus.MEMBERID_INVALID);
        }
    }
}
