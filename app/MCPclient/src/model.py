import asyncio

from langchain_mcp_adapters.client import MultiServerMCPClient
from langchain_openai import ChatOpenAI
from src.settings import settings

model = ChatOpenAI(
    model='gpt-4o',
    temperature=0.3,
    max_retries=2,
    base_url=settings.BASE_URL,
    api_key=settings.API_KEY
)

client = MultiServerMCPClient({
    "vacation-pay-mcp-server": {
        "transport": "http",
        "url": settings.VACATION_PAY_URL
    }
})

tools = asyncio.run(client.get_tools())
llm = model.bind_tools(tools)
