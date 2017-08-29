(function () {
    fetch('data/papes-par-saintete.json')
        .then(response => response.json())
        .then(json => draw(json))

    const draw = (json) => {
        const columns = json.map(item => [item.label, item.value])
        const colors = {}
        colors[columns[1][0]] = '#e1b941'
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
})()