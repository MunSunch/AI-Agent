from langgraph.constants import END, START
from langgraph.graph import MessagesState, StateGraph
from langgraph.prebuilt import ToolNode, tools_condition
from src.model import llm, tools

class State(MessagesState):
    pass

async def call_model(state: State) -> State:
    return {"messages": await llm.ainvoke(state['messages'])}

graph = StateGraph(State)
graph.add_node("model", call_model)
graph.add_node("tools", ToolNode(tools))

graph.add_edge(START, "model")
graph.add_conditional_edges("model", tools_condition)
graph.add_edge("tools", "model")
graph.add_edge("model", END)

agent = graph.compile()