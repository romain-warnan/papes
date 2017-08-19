const context = document.getElementById('papes-par-saintete').getContext('2d')

Chart.defaults.global.legend.position = 'bottom'

fetch('data/papes-par-saintete.json')
    .then(response => response.json())
    .then(json => draw(json))

const draw = (json) => {
    const labels = json.map(item => item.label)
    const values = json.map(item => item.value)

    const chart = new Chart(context, {
        type: 'doughnut',
        label: 'Papes canonisés',

        data: {
            datasets: [{
                data: values,
                borderColor: [
                    'rgba(251,147,20, 1)',
                    'rgba(65, 105, 225, 1)'
                ],
                backgroundColor: [
                    'rgba(251,147,20, 0.5)',
                    'rgba(65, 105, 225, 0.5)',
                ],
                borderWidth: 2
            }],
            labels: labels
        },
        options: {
            rotation: Math.PI,
            tooltips: {
                callbacks: {
                    label: tooltipLabel
                }
            }
        }
    })
}

const tooltipLabel = (tooltipItem, data) => {
    const index = tooltipItem.index
    const datasetIndex = tooltipItem.datasetIndex
    const values = data.datasets[datasetIndex].data
    const total = _.sum(values);
    const value = values[index]
    const percent = _.round(value * 100 / total)

    return `${percent} % (${value})`
}