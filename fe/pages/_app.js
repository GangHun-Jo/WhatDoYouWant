import React from 'react';
import App, {Container} from 'next/app';
import Head from 'next/head';
import CssBaseline from '@material-ui/core/CssBaseline';


export default class RootApp extends App {
	componentDidMount() {
		// Remove the server-side injected CSS.
		const jssStyles = document.querySelector('#jss-server-side');
		if (jssStyles) {
			jssStyles.parentNode.removeChild(jssStyles);
		}
	}

	render() {
		const {Component, ...other} = this.props;
		return (
			<Container>
				<Head>
					<title>Static Website</title>
				</Head>
				<CssBaseline/>
				<Component {...other} />
			</Container>
		);
	}
}
