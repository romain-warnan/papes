const Chart = require('chart.js')
const c3 = require('c3')
const d3 = require('d3')
const _ = require('lodash');(function () {
    fetch('data/papes-par-saintete.json')
        .then(response => response.json())
        .then(json => draw(json))

    const draw = (json) => {
        const columns = json.map(item => [item.label, item.value])
        const colors = {}
        colors[columns[0][1]] = '#e1b941'
        colors[columns[0][0]] = '#4169e1'
        c3.generate({
            bindto: '#papes-par-saintete',
            data: {
                type: 'donut',
                columns: columns,
                colors: colors
            }
        });
    }
})();(function () {
    fetch('data/plus-courts-regnes.json')
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
            bindto: '#plus-courts-regnes',
            data: {
                type: 'bar',
                x: 'labels',
                columns: [labels, values],
                colors: {
                    values: '#a90f27'
                },
                color: function (color, d) {
                    return d.id && d.id === 'values' ? d3.rgb(color).brighter(0.13 * (9 - d.index)) : color
                }
            },
            axis: {
                rotated: true,
                x: {
                    type: 'category'
                }
            },
            tooltip: {
                format: {
                    name: (name, ratio, id, index) => 'Règne',
                    value: (value, ratio, id, index) => tooltipValues[index]
                }
            },
            legend: {
                hide: 'values'
            }
        });
    }
})();(function () {
    fetch('data/plus-longs-regnes.json')
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
                    values: '#009688'
                },
                color: function (color, d) {
                    return d.id && d.id === 'values' ? d3.rgb(color).brighter(0.13 * d.index) : color
                }
            },
            axis: {
                rotated: true,
                x: {
                    type: 'category'
                }
            },
            tooltip: {
                format: {
                    name: (name, ratio, id, index) => 'Règne',
                    value: (value, ratio, id, index) => tooltipValues[index]
                }
            },
            legend: {
                hide: 'values'
            }
        });
    }
})()