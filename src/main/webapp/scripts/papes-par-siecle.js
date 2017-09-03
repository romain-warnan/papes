import c3 from 'c3'

const load = () => fetch('data/papes-par-siecle.json')
    .then(response => response.json())
    .then(json => draw(json))

const draw = (json) => {

    const labels = json.map(item => item.label)
    console.log(labels)

    const rows = json.map(item => item.value)
    rows.unshift(labels)
    console.log(rows)

    c3.generate({
        bindto: '#papes-par-siecle',
        data: {
            type: 'bar',
            rows: rows,
        },
        legend: {
            hide: 'values',
        },
        axis: {
            x: {
                type: 'category',
                categories: labels
            }
        },
        groups: [
            labels,
        ]
    });
}

export default load