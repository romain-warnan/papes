import c3 from 'c3'

const load = () => fetch('data/papes-par-nationalite.json')
    .then(response => response.json())
    .then(json => draw(json))

const draw = (json) => {

    const columns = json.map(item => [item.label, item.value])
    const labels = json.map(item => item.label)
    const values = json.map(item => item.value)

    c3.generate({
        bindto: '#papes-par-nationalite',
        data: {
            type: 'donut',
            columns: columns,
            // colors: colors,
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