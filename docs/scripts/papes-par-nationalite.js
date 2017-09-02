import c3 from 'c3'

const load = () => fetch('data/papes-par-nationalite.json')
    .then(response => response.json())
    .then(json => draw(json))

const draw = (json) => {

    const columns = json.map(item => [item.label, item.value])
    const labels = json.map(item => item.label)
    const values = json.map(item => item.value)

    const colors = {}
    colors[columns[0][0]] = '#c55a77'
    colors[columns[1][0]] = '#793939'
    colors[columns[2][0]] = '#cdd5c6'
    colors[columns[3][0]] = '#a68ba5'
    colors[columns[4][0]] = '#094eac'
    colors[columns[5][0]] = '#fb421d'
    colors[columns[6][0]] = '#1b998b'
    colors[columns[7][0]] = '#ffda8f'

    c3.generate({
        bindto: '#papes-par-nationalite',
        data: {
            type: 'donut',
            columns: columns,
            colors: colors,
        },
        tooltip: {
            format: {
                name: (name, ratio, id, index) => labels[index],
                value: (value, ratio, id, index) => {
                    const number = values[index]
                    return `${number} (${Math.round(100 * 10 * ratio) / 10} %)`
                },
            },
        },
    })
}

export default load