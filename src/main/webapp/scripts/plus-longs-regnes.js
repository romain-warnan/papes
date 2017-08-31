import c3 from 'c3'

const load = () => fetch('data/plus-longs-regnes.json')
    .then(response => response.json())
    .then(json => draw(json))

const draw = (json) => {
    const labels = json
        .map(item => item.label)
        .map(label => label.substring(0, label.indexOf(' : ')))

    labels.unshift('labels')

    const values = json.map(item => item.value)
    values.unshift('values')

    const tooltipValues = json
        .map(item => item.label)
        .map(label => label.substring(label.indexOf(' : ') + 3, label.length))

    c3.generate({
        bindto: '#plus-longs-regnes',
        data: {
            type: 'bar',
            x: 'labels',
            columns: [labels, values],
            colors: {
                values: '#009688',
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
        tooltip: {
            format: {
                name: (name, ratio, id, index) => 'Règne',
                value: (value, ratio, id, index) => tooltipValues[index],
            },
        },
        legend: {
            hide: 'values',
        }
    });
}

export default load;