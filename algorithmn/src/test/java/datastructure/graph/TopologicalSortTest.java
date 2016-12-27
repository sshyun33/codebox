package datastructure.graph;


import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * <알고리즘 비밀의 문을 열다> 127쪽 프로시저 풀이
 */
public class TopologicalSortTest {

    @Test
    public void testTopologicalSort() {
        MutableGraph<String> hockeyEquipGraph = createHockeyEquipGraph();
        Map<String, Integer> inDegree = createInDegreeMap(hockeyEquipGraph);
        Stack<String> next = createNextStack(inDegree);

        List<String> result = new ArrayList<>();
        while (!next.isEmpty()) {
            String nextNode = next.pop();
            result.add(nextNode);
            for (String successor : hockeyEquipGraph.successors(nextNode)) {
                inDegree.computeIfPresent(successor, (k, v) -> v = v - 1);
                if (inDegree.get(successor) == 0) next.push(successor);
            }
        }

        assertTrue(inDegree.values().stream().allMatch(v -> v == 0));

        List<String> expected = Arrays.asList(
                "속바지", "레깅스", "컵", "양말", "호스",
                "바지", "스케이트", "다리보호대", "티셔츠", "가슴보호대",
                "스웨터", "마스크", "캐치글로브", "블로커");
        assertThat(result, is(expected));

    }

    private MutableGraph<String> createHockeyEquipGraph() {
        List<String> hockeyEquipments = Arrays.asList("속바지", "양말", "레깅스", "호스", "컵",
                "바지", "스케이트", "다리보호대", "티셔츠", "가슴보호대",
                "스웨터", "마스크", "캐치글로브", "블로커");

        MutableGraph<String> hockeyEquipGraph = GraphBuilder.directed().build();

        hockeyEquipments.forEach(hockeyEquipGraph::addNode);

        hockeyEquipGraph.putEdge("속바지", "레깅스");
        hockeyEquipGraph.putEdge("양말", "호스");
        hockeyEquipGraph.putEdge("레깅스", "호스");
        hockeyEquipGraph.putEdge("레깅스", "컵");
        hockeyEquipGraph.putEdge("호스", "바지");
        hockeyEquipGraph.putEdge("컵", "바지");
        hockeyEquipGraph.putEdge("바지", "스케이트");
        hockeyEquipGraph.putEdge("바지", "스웨터");
        hockeyEquipGraph.putEdge("스케이트", "다리보호대");
        hockeyEquipGraph.putEdge("다리보호대", "캐치글로브");
        hockeyEquipGraph.putEdge("티셔츠", "가슴보호대");
        hockeyEquipGraph.putEdge("가슴보호대", "스웨터");
        hockeyEquipGraph.putEdge("스웨터", "마스크");
        hockeyEquipGraph.putEdge("마스크", "캐치글로브");
        hockeyEquipGraph.putEdge("캐치글로브", "블로커");

        return hockeyEquipGraph;
    }

    private Map<String, Integer> createInDegreeMap(MutableGraph<String> hockeyEquipGraph) {
        Map<String, Integer> inDegree = hockeyEquipGraph.nodes().stream()
                .collect(Collectors.toMap(
                        Function.identity(), count -> 0,
                        (v1, v2) -> {
                            throw new IllegalStateException("duplicate key");
                        }, LinkedHashMap::new));


        for (String node : hockeyEquipGraph.nodes()) {
            hockeyEquipGraph.successors(node).forEach(
                    successor -> inDegree.computeIfPresent(successor, (k, v) -> v = v + 1));
        }
        return inDegree;
    }

    private Stack<String> createNextStack(Map<String, Integer> inDegree) {
        Stack<String> next = new Stack<>();
        inDegree.entrySet().stream()
                .filter(e -> e.getValue() == 0)
                .forEach(e -> next.push(e.getKey()));
        Collections.reverse(next);
        return next;
    }
}