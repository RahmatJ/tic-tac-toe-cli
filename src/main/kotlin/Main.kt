import state.InitState
import state.State

fun main() {
    var state: State = InitState()
    while (true) {
        if (state.isDone) {
            break
        }
        state = state.run()
    }

}