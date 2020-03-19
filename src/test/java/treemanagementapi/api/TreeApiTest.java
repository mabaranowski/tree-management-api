package treemanagementapi.api;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import treemanagementapi.model.rest.Node;
import treemanagementapi.service.NodeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TreeApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NodeService nodeService;

    @Test
    public void whenGetTreeStructure_thenReturn200() throws Exception {
        mvc.perform(
        get("/retrieve")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void whenGetTreeStructure_thenReturnProperSize() throws Exception {
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node());
        
        when(nodeService.getTree()).thenReturn(nodeList);

        mvc.perform(
        get("/retrieve")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void whenGetTreeStructure_thenReturnProperData() throws Exception {
        final String key = "key123";
        final Long value = 123L;
        final String parent = null;

        List<Node> nodeList = new ArrayList<>();
        Node node = new Node();
        node.setKey(key);
        node.setValue(value);
        node.setParent(parent);
        nodeList.add(node);
        
        when(nodeService.getTree()).thenReturn(nodeList);

        mvc.perform(
        get("/retrieve")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].key", is(key)))
        .andExpect(jsonPath("$[0].value", is(Integer.parseInt(value.toString()))))
        .andExpect(jsonPath("$[0].parent", is(parent)));
    }

    @Test
    public void whenSaveTreeStructure_thenIfCorrectBodyReturn200() throws Exception {
        String content = "[{\"key\":\"e07850ba-8c6c-475c-a41b-341038c82611\",\"value\":0,\"parent\":\"c2911fb8-2026-4cea-9f51-d472dd341aa6\"}]";

        mvc.perform(
        post("/save")
        .content(content)
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void whenSaveTreeStructure_thenIfEmptyBodyReturn400() throws Exception {
        String content = "";

        mvc.perform(
        post("/save")
        .content(content)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

}