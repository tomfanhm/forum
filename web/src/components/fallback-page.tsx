import Link from "next/link"

import { Button } from "./ui/button"

type FallbackPageProps = {
  status: number
  title: string
  description: string
}

const FallbackPage: React.FC<FallbackPageProps> = ({
  status,
  title,
  description,
}) => {
  return (
    <main className="bg-background grid min-h-screen place-items-center px-6 py-24 sm:py-32 lg:px-8">
      <div className="text-center">
        <p className="text-primary text-base font-semibold">{status}</p>
        <h1 className="text-foreground mt-4 text-5xl font-semibold tracking-tight text-balance sm:text-7xl">
          {title}
        </h1>
        <p className="text-muted-foreground mt-6 text-lg font-medium text-pretty sm:text-xl/8">
          {description}
        </p>
        <div className="mt-10 flex items-center justify-center gap-x-6">
          <Button asChild>
            <Link href="/">Go back home</Link>
          </Button>
          <Button variant="outline" asChild>
            <Link href="/contact">Contact support</Link>
          </Button>
        </div>
      </div>
    </main>
  )
}
export default FallbackPage
