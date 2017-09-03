import c3 from 'c3'

const load = () => fetch('data/papes-par-nom-de-regne.json')
    .then(response => response.json())
    .then(json => draw(json))

const draw = (json) => {
    const labels = json.map(item => item.label)
    labels.unshift('labels')

    const values = json.map(item => item.value)
    values.unshift('values')

    c3.generate({
        bindto: '#papes-par-nom-de-regne',
        data: {
            type: 'bar',
            x: 'labels',
            columns: [labels, values],
            colors: {
                values: '#68537e',
            },
            color: function (color, d) {
                return d.id && d.id === 'values' ? d3.rgb(color).brighter(0.13 * d.index) : color
            },
        },
        axis: {
            rotated: true,
            x: {
                type: 'category',
            },
        },
        legend: {
            hide: 'values',
        },
        tooltip: {
            format: {
                name: (name, ratio, id, index) => 'Papes',
            },
        },
    });
}

export default load