export default function movimiento(id: any, card: any) {
    let res = false
    let figs = card.figures
    console.log(figs)
    for (var fig of figs) {
        res = res || fig.id === id
    }

    return res;
}