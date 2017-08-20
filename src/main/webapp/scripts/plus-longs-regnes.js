(function () {
    fetch('data/plus-longs-regnes.json')
        .then(response => response.json())
        .then(json => draw(json))

    const draw = (json) => {
        const labels = json
            .map(item => item.label)
            .map(label => label.substring(0, label.indexOf(' : ')))

        labels.unshift('Papes')

        const values = json.map(item => item.value)
        values.unshift('Durée du pontificat en jours')

        const tooltipValues = json
            .map(item => item.label)
            .map(label => label.substring(label.indexOf(' : ') + 3, label.length))

        c3.generate({
            bindto: '#plus-longs-regnes',
            data: {
                type: 'bar',
                x: 'Papes',
                columns: [labels, values]
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
            }
        });
    }
})()