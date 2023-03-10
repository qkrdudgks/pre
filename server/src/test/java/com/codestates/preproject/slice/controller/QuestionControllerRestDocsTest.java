//package com.codestates.preproject.slice.controller;
//
//import com.codestates.preproject.question.controller.QuestionController;
//import com.codestates.preproject.question.dto.QuestionDto;
//import com.codestates.preproject.question.entity.Question;
//import com.codestates.preproject.question.mapper.QuestionMapper;
//import com.codestates.preproject.question.service.QuestionService;
//import com.codestates.preproject.tag.dto.TagDto;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static com.codestates.preproject.utils.ApiDocumentUtils.getRequestPreProcessor;
//import static com.codestates.preproject.utils.ApiDocumentUtils.getResponsePreProcessor;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
//import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(QuestionController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class QuestionControllerRestDocsTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private QuestionService questionService;
//
//    @MockBean
//    private QuestionMapper questionMapper;
//
//    @Autowired
//    private Gson gson;
//
//    @Test
//    void postQuestionTest() throws Exception {
//        // given
//        QuestionDto.Post postDto = new QuestionDto.Post(1L,
//                "?????? ??????1 - ????????? ??? ????????????? oo??? ?????? xx??? ???????????? ??? ?????????",
//                "?????? ??????1?????????. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ????????? ??? ?????????????",
//                Arrays.asList(new TagDto.Request(1L), new TagDto.Request(2L), new TagDto.Request(3L)));
//        String content = gson.toJson(postDto);
//
//        QuestionDto.SimpleResponse responseDto = new QuestionDto.SimpleResponse(1L,
//                1L,
//                "orangetree",
//                "?????? ??????1 - ????????? ??? ????????????? oo??? ?????? xx??? ???????????? ??? ?????????",
//                "?????? ??????1?????????. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ????????? ??? ?????????????",
//                0L,
//                List.of(new TagDto.Response(1L, "JavaScript"), new TagDto.Response(2L, "Java"), new TagDto.Response(3L, "SQL")),
//                0L,
//                LocalDateTime.now(),
//                LocalDateTime.now());
//
//        given(questionMapper.questionPostDtoToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());
//
//        Question mockResultQuestion = new Question();
//        mockResultQuestion.setQuestionId(1L);
//        given(questionService.createQuestion(Mockito.any(Question.class), Mockito.anyLong())).willReturn(mockResultQuestion);
//
//        given(questionMapper.questionToQuestionSimpleResponseDto(Mockito.any(Question.class))).willReturn(responseDto);
//
//        // when
//        ResultActions actions = mockMvc.perform(post("/questions")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content)
//        );
//
//        // then
//        actions.andExpect(status().isCreated())
////                .andExpect(header().string("Location", is(startsWith("/questions"))))
//                .andDo(document("post-question",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("questionTitle").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("questionContent").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("?????? ?????? ??????").optional(),
//                                        fieldWithPath("tags[].tagId").type(JsonFieldType.NUMBER).description("?????? ?????????").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
//                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data.questionTitle").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data.questionContent").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data.views").type(JsonFieldType.NUMBER).description("?????? ?????? ???"),
//                                        fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
//                                        fieldWithPath("data.tags[].tagId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data.tags[].tagWord").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data.answerCount").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                        fieldWithPath("data.lastModifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ?????????")
//                                )
//                        )
////                        responseHeaders(headerWithName(HttpHeaders.LOCATION).description("Location header; ????????? ???????????? URI"))
//                ));
//    } // postQuestionTest() ????????? ??????
//
//    @Test
//    void patchQuestionTest() throws Exception {
//        // setup - run test - tear down
//        // given
//        LocalDateTime time1 = LocalDateTime.now();
//        Long questionId = 1L;
//
//        QuestionDto.Patch patchDto = new QuestionDto.Patch(1L, "??????1 ?????? ?????? - ????????? ???????????????? ???????????? ???????????????", "??????1 ?????? ???????????????. It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). ????????? ????????????????", Arrays.asList(new TagDto.Request(4L), new TagDto.Request(2L)));
//        String content = gson.toJson(patchDto);
//
//        QuestionDto.SimpleResponse responseDto = new QuestionDto.SimpleResponse(1L,
//                1L,
//                "orangetree",
//                "??????1 ?????? ?????? - ????????? ???????????????? ???????????? ???????????????",
//                "??????1 ?????? ???????????????. It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like). ????????? ????????????????",
//                0L,
//                Arrays.asList(new TagDto.Response(4L, "Spring"), new TagDto.Response(2L, "Java")),
//                5L,
//                time1,
//                LocalDateTime.now());
//
//        given(questionMapper.questionPatchDtoToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());
//        given(questionService.updateQuestion(Mockito.any(Question.class))).willReturn(new Question());
//        given(questionMapper.questionToQuestionSimpleResponseDto(Mockito.any(Question.class))).willReturn(responseDto);
//
//        // when
//        ResultActions actions = mockMvc.perform(patch("/questions/{question-id}", questionId)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content));
//
//        // then
//        actions.andExpect(status().isOk())
////                .andExpect(header().string("Location", is(startsWith("/questions"))))
//                .andDo(document("patch-question",
//                        getRequestPreProcessor(),
//                        getResponsePreProcessor(),
//                        pathParameters(parameterWithName("question-id").description("?????? ?????????")),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("?????? ?????????").ignored(),
////                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("questionTitle").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("questionContent").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("tags").type(JsonFieldType.ARRAY).description("?????? ?????? ??????").optional(),
//                                        fieldWithPath("tags[].tagId").type(JsonFieldType.NUMBER).description("?????? ?????????").optional()
//                                )
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
//                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data.questionTitle").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data.questionContent").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data.views").type(JsonFieldType.NUMBER).description("?????? ?????? ???"),
//                                        fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
//                                        fieldWithPath("data.tags[].tagId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data.tags[].tagWord").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data.answerCount").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                        fieldWithPath("data.lastModifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ?????????")
//                                )
//                        )
//                ));
//    } // patchQuestionTest() ????????? ??????
//
//    @Test
//    void getQuestionTest() throws Exception {
//        Long questionId = 1L;
//
//        QuestionDto.DetailResponse responseDto = new QuestionDto.DetailResponse(1L,
//                1L,
//                "orangetree",
//                "?????? ??????1 - ????????? ??? ????????????? oo??? ?????? xx??? ???????????? ??? ?????????",
//                "?????? ??????1?????????. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ????????? ??? ?????????????",
//                14L,
//                List.of(new TagDto.Response(1L, "JavaScript"), new TagDto.Response(2L, "Java"), new TagDto.Response(3L, "SQL")),
//                List.of(
//                        new AnswerDto.Response(1L,
//                                2L,
//                                "purplegrape"
//                                , 1L,
//                                "??????1??? ?????? ??????2??? ?????????(1)?????????",
//                                3L,
//                                false,
//                                LocalDateTime.now(),
//                                LocalDateTime.now()
//                        ),
//                        new AnswerDto.Response(2L,
//                                3L,
//                                "yellowflower",
//                                1L,
//                                "??????1??? ?????? ??????3??? ?????????(2)?????????",
//                                1L,
//                                true,
//                                LocalDateTime.now(),
//                                LocalDateTime.now()
//                        ),
//                        new AnswerDto.Response(3L,
//                                4L,
//                                "greencomputer",
//                                1L,
//                                "??????1??? ?????? ??????4??? ?????????(3)?????????",
//                                -1L,
//                                true,
//                                LocalDateTime.now(),
//                                LocalDateTime.now()
//                        )
//                ),
//                3L,
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        );
//
//        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());
//        given(questionMapper.questionToQuestionDetailResponseDto(Mockito.any(Question.class))).willReturn(responseDto);
//
//        // when
//        ResultActions actions = mockMvc.perform(get("/questions/{question-id}", questionId).accept(MediaType.APPLICATION_JSON));
//
//        // then
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.questionId").value(questionId))
//                .andDo(document("get-question",
//                        getResponsePreProcessor(),
//                        pathParameters(parameterWithName("question-id").description("?????? ?????????")),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),
//                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data.nickname").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data.questionTitle").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data.questionContent").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data.views").type(JsonFieldType.NUMBER).description("?????? ?????? ???"),
//                                        fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
//                                        fieldWithPath("data.tags[].tagId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data.tags[].tagWord").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data.answers").type(JsonFieldType.ARRAY).description("?????? ??????").optional(),
//                                        fieldWithPath("data.answers[].answerId").type(JsonFieldType.NUMBER).description("?????? ?????????").optional(),
//                                        fieldWithPath("data.answers[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????").optional(),
//                                        fieldWithPath("data.answers[].nickname").type(JsonFieldType.STRING).description("?????? ?????? ?????????").optional(),
//                                        fieldWithPath("data.answers[].questionId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????? ?????????").optional(),
//                                        fieldWithPath("data.answers[].answerContent").type(JsonFieldType.STRING).description("?????? ??????").optional(),
//                                        fieldWithPath("data.answers[].voteCount").type(JsonFieldType.NUMBER).description("?????? ??????").optional(),
//                                        fieldWithPath("data.answers[].isVoted").type(JsonFieldType.BOOLEAN).description("????????? ??????").optional(),
//                                        fieldWithPath("data.answers[].createdAt").type(JsonFieldType.STRING).description("?????? ?????????").optional(),
//                                        fieldWithPath("data.answers[].lastModifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ?????????").optional(),
//                                        fieldWithPath("data.answerCount").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                        fieldWithPath("data.lastModifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ?????????")
//                                )
//                        )
//                ));
//    } // getQuestionTest() ????????? ????????? ??????
//
//    // ??????4a = 'top questions' = ??????????????? = ?????? ?????? ?????? + default??? newest????????? ??????
//    @Test
//    void getQuestionsByCreatedAtTest() throws Exception {
//        // given
//        Integer page = 1;
//        Integer size = 10;
//
//        List<Question> questions = List.of(new Question(), new Question(), new Question());
//
//        List<QuestionDto.SimpleResponse> responseDtos = List.of(
//                new QuestionDto.SimpleResponse(3L, 1L, "orangetree", "?????? ??????3", "?????? ??????3?????????. ????????? ????????? ????????????????", 10L, List.of(new TagDto.Response(1L, "JavaScript"), new TagDto.Response(2L, "Java"), new TagDto.Response(3L, "SQL")), 3L, LocalDateTime.now(), LocalDateTime.now()),
//                new QuestionDto.SimpleResponse(2L, 2L, "purplegrape", "?????? ??????2", "?????? ??????2?????????. ????????? ??? ?????? ??????????", 7L, List.of(new TagDto.Response(7L, "C++")), 2L, LocalDateTime.now(), LocalDateTime.now()),
//                new QuestionDto.SimpleResponse(1L, 1L, "orangetree", "?????? ??????1", "?????? ??????1?????????. ????????? ????????????????", 15L, List.of(new TagDto.Response(4L, "Spring"), new TagDto.Response(5L, "React")), 5L, LocalDateTime.now(), LocalDateTime.now())
//        );
//
//        given(questionService.findQuestionsByCreatedAt(Mockito.anyInt(), Mockito.anyInt())).willReturn(new PageImpl<>(questions, PageRequest.of(page - 1, size, Sort.by("questionId").descending()), questions.size()));
//        given(questionMapper.questionsToQuestionSimpleResponseDtos(Mockito.anyList())).willReturn(responseDtos);
//
//        // when
//        ResultActions actions = mockMvc.perform(
//                get("/questions?page={page}&size={size)}", page, size)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        // then
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray())
//                .andDo(document("get-questions",
//                        getResponsePreProcessor(),
//                        requestParameters(List.of(
//                                parameterWithName("page").description("????????? ??????"),
//                                parameterWithName("size").description("????????? ??????"))),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????"),
//                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data[].nickname").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data[].questionTitle").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data[].questionContent").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data[].views").type(JsonFieldType.NUMBER).description("?????? ?????? ???"),
//                                        fieldWithPath("data[].tags").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
//                                        fieldWithPath("data[].tags[].tagId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data[].tags[].tagWord").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data[].answerCount").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                        fieldWithPath("data[].lastModifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
//
//                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("?????????????????? ??????"),
//                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("?????? ????????? ??????"),
//                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("????????? ??????"),
//                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("????????? ??? ??????"),
//                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???")
//                                )
//                        )
//                ));
//    }
//
//    // ??????4c = '??????????????? ??? ?????????' = ?????? ????????? ?????? ?????? ?????? + default??? ????????? ?????????????????? ??????(+newest ??? ????????? ??????) = ????????? '???'
//    // ?????? ur ?????? = https://stackoverflow.com/search?q=java+jpa+sql
//    @Test
//    void searchQuestionsByTitleOrContentTest() throws Exception {
//        // given
//        Integer page = 1;
//        Integer size = 15;
//        String searchText = "Java ??????";
//
//        List<Question> questions = List.of(new Question(), new Question(), new Question());
//
//        List<QuestionDto.SimpleResponse> responseDtos = List.of(
//                new QuestionDto.SimpleResponse(3L, 1L, "orangetree", "Java ?????? ??????3", "?????? ??????3?????????. ????????? ????????? ????????????????", 16L, List.of(new TagDto.Response(1L, "JavaScript"), new TagDto.Response(2L, "Java"), new TagDto.Response(3L, "SQL")), 11L, LocalDateTime.now(), LocalDateTime.now()),
//                new QuestionDto.SimpleResponse(9L, 2L, "purplegrape", "?????? ??????9", "?????? ??????9?????????. ?????? ????????? ??? ?????? ??????????", 10L, List.of(new TagDto.Response(7L, "C++")), 7L, LocalDateTime.now(), LocalDateTime.now()),
//                new QuestionDto.SimpleResponse(11L, 1L, "orangetree", "?????? ??????11", "?????? ??????11?????????. java?????? ????????? ????????????????", 5L, List.of(new TagDto.Response(4L, "Spring"), new TagDto.Response(5L, "React")), 5L, LocalDateTime.now(), LocalDateTime.now())
//        );
//
//        given(questionService.findQuestionsByTitleOrContent(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).willReturn(new PageImpl<>(questions, PageRequest.of(page - 1, size, Sort.by("answerCount").descending()), questions.size()));
//        given(questionMapper.questionsToQuestionSimpleResponseDtos(Mockito.anyList())).willReturn(responseDtos);
//
//        // when
//        ResultActions actions = mockMvc.perform(
//                get("/questions/search?q={searchText}&page={page}&size={size}", searchText, page, size)
//                        .accept(MediaType.APPLICATION_JSON)
//        );
//
//        // then
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray())
//                .andDo(document("search-questions-by-title-or-content",
//                        getResponsePreProcessor(),
//                        requestParameters(List.of(
//                                parameterWithName("q").description("?????????"),
//                                parameterWithName("page").description("????????? ??????"),
//                                parameterWithName("size").description("????????? ??????"))),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("?????? ?????????"),
//                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data[].nickname").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
//                                        fieldWithPath("data[].questionTitle").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data[].questionContent").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data[].views").type(JsonFieldType.NUMBER).description("?????? ?????? ???"),
//                                        fieldWithPath("data[].tags").type(JsonFieldType.ARRAY).description("?????? ?????? ??????"),
//                                        fieldWithPath("data[].tags[].tagId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data[].tags[].tagWord").type(JsonFieldType.STRING).description("?????? ??????"),
//                                        fieldWithPath("data[].answerCount").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                        fieldWithPath("data[].lastModifiedAt").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
//
//                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("?????????????????? ??????"),
//                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("?????? ????????? ??????"),
//                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("????????? ??????"),
//                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("????????? ??? ??????"),
//                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    void deleteQuestionTest() throws Exception {
//        // given
//        Long questionId = 1L;
//
////        given(questionService.deleteQuestion(Mockito.anyLong())).willReturn(); // service ?????? ??????????????? ???????????? ??? ???????????? ?????? ?????? ??????
//
//        // when
//        ResultActions actions = mockMvc.perform(
//                delete("/questions/{question-id}", questionId));
//
//        // then
//        actions.andExpect(status().isNoContent())
//                .andDo(document("delete-question",
//                        pathParameters(parameterWithName("question-id").description("?????? ?????????"))));
//    }
//
//}