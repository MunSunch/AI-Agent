import { Telegraf } from 'telegraf';
import { message } from 'telegraf/filters';
import 'dotenv/config';

const bot = new Telegraf(process.env.BOT_TOKEN!)

bot.start((ctx) => ctx.reply('Привет, я LLM, умею рассчитывать отпускные'))
bot.on(message('text'), async (ctx) => {
    const request = {
        message: ctx.message.text
    }
    const response = await fetch(process.env.AI_AGENT_URL!, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(request)
    })
    const answerLLM = await response.json()
        .then(result => {
            return result.message
        })
    await ctx.telegram.sendMessage(ctx.message.chat.id, answerLLM)
})
bot.launch()

process.once('SIGINT', () => bot.stop('SIGINT'))
process.once('SIGTERM', () => bot.stop('SIGTERM'))