import ChartsPage from "./modules/playedGames/ChartsPage";

const Patata = () => {
    const freq = new Map();
    freq.set("1", 2)
    freq.set("2", 1)
    freq.set("3", 3)
    freq.set("4", 8)
    freq.set("5", 7)

    return <ChartsPage frequency={freq} />
}

export default Patata;