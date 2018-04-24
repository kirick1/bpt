import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FSMTest {
    @ParameterizedTest
    @ValueSource(strings = {"1112E11E1", "111E11E2"})
    @MethodSource("dataProviderIsMatchString")
    @CsvFileSource(resources = "/isMatchStrings.csv")
    @ArgumentsSource(MyArgumentsProvider.class)
    void isMatchStrings(String s) {
        FSMSwitch fsmSwitch = new FSMSwitch();
        assertTrue(fsmSwitch.fsm(s));
        FSMTable fsmTable = new FSMTable();
        assertTrue(fsmTable.fsm(s));
        FSMState fsm = new FSMState();
        assertTrue(fsm.fsm(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1E11", "111E11EEE2"})
    @MethodSource("dataProviderNotMatchString")
    @CsvFileSource(resources = "/notMatchStrings.csv")
    void notMatchStrings(String s) {
        FSMSwitch fsmSwitch = new FSMSwitch();
        assertFalse(fsmSwitch.fsm(s));
        FSMTable fsmTable = new FSMTable();
        assertFalse(fsmTable.fsm(s));
        FSMState fsm = new FSMState();
        assertFalse(fsm.fsm(s));
    }

    private static Stream<Arguments> dataProviderIsMatchString() {
        return Stream.of(Arguments.of("1112E11E1"),
                Arguments.of("111E11E2"));
    }

    private static Stream<Arguments> dataProviderNotMatchString() {
        return Stream.of(Arguments.of("1E11"),
                Arguments.of("111E11EEE2"));
    }

    static class MyArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of("1112E11E1", "111E11E2").map(Arguments::of);
        }
    }
}
