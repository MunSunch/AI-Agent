from fastapi import FastAPI
from pydantic import BaseModel
from src.agent import agent

app = FastAPI(title="AI Agent API")

class Request(BaseModel):
    message: str

class Response(BaseModel):
    message: str

@app.post("/", response_model=Response)
async def root(request: Request):
    answer = await agent.ainvoke({"messages": request.message})
    return {"message": answer["messages"][-1].content}